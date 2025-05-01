package nextstep.session.domain;

import nextstep.payments.domain.FreePaymentPolicy;
import nextstep.payments.domain.PaidPaymentPolicy;
import nextstep.payments.domain.Payment;
import nextstep.payments.domain.PaymentPolicy;
import nextstep.session.domain.image.CoverImage;
import nextstep.session.domain.image.CoverImages;
import nextstep.session.domain.session.*;
import nextstep.session.domain.student.EnrolledStudents;
import nextstep.session.domain.student.EnrollmentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static nextstep.users.domain.NsUserTest.JAVAJIGI;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

class SessionTest {

    private CoverImages coverImages = new CoverImages(new ArrayList<>());
    private Duration duration;

    @BeforeEach
    public void setUp() {
        coverImages.add(
                new CoverImage.Builder()
                        .fileName("cover.png")
                        .imageFormat("png")
                        .fileSize(100_000L)
                        .imageSize(300, 200)
                        .sessionId(1L)
                        .build()
        );
        duration = new Duration(
                LocalDate.of(2025, 5, 1),
                LocalDate.of(2025, 5, 30));
    }

    @Test
    @DisplayName("정상적인 유료 강의 생성")
    void createPaidSession_success() {
        PaymentPolicy paidPaymentPolicy = new PaidPaymentPolicy( 800_000L, 1);
        Session paidSession = new SessionBuilder()
                .title("TDD, 클린코드 with Java 20기")
                .coverImages(coverImages)
                .duration(duration)
                .paymentPolicy(paidPaymentPolicy)
                .enrolledStudents(new EnrolledStudents())
                .sessionStatus(SessionStatus.PREPARING)
                .recruitmentStatus(RecruitmentStatus.OPEN)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        assertThat(paidSession.title()).isEqualTo("TDD, 클린코드 with Java 20기");
    }

    @Test
    @DisplayName("정상적인 무료 강의 생성")
    void createFreeSession_success() {
        PaymentPolicy freePaymentPolicy = new FreePaymentPolicy();
        Session freeSession = new SessionBuilder()
                .title("무료 강의")
                .coverImages(coverImages)
                .duration(duration)
                .paymentPolicy(freePaymentPolicy)
                .enrolledStudents(new EnrolledStudents())
                .sessionStatus(SessionStatus.PREPARING)
                .recruitmentStatus(RecruitmentStatus.OPEN)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        assertThat(freeSession.title()).isEqualTo("무료 강의");
    }

    @Test
    @DisplayName("유료 강의를 수강하면 Payment 객체를 반환한다.")
    void paidSession_enrollReturnPayment() {
        PaymentPolicy paidPaymentPolicy = new PaidPaymentPolicy( 800_000L, 1);
        Session paidSession = new SessionBuilder()
                .title("TDD, 클린코드 with Java 20기")
                .coverImages(coverImages)
                .duration(duration)
                .paymentPolicy(paidPaymentPolicy)
                .sessionStatus(SessionStatus.PREPARING)
                .recruitmentStatus(RecruitmentStatus.OPEN)
                .build();

        Payment payment = paidSession.enroll(JAVAJIGI, 800_000L);

        assertThat(payment).isNotNull();
    }

    @ParameterizedTest
    @EnumSource(value = SessionStatus.class, names = {"PREPARING", "IN_PROGRESS"})
    @DisplayName("강의가 모집중이거나 진행중일 때 수강신청이 가능해야 한다.")
    void preparingOrInProgressSessionStatus_canEnroll(SessionStatus sessionStatus) {
        PaymentPolicy paidPaymentPolicy = new PaidPaymentPolicy(800_000L, 1);
        Session paidSession = new SessionBuilder()
                .title("TDD, 클린코드 with Java 20기")
                .coverImages(coverImages)
                .duration(duration)
                .paymentPolicy(paidPaymentPolicy)
                .sessionStatus(sessionStatus)
                .recruitmentStatus(RecruitmentStatus.OPEN)
                .build();

        Payment payment = paidSession.enroll(JAVAJIGI, 800_000L);

        assertThat(payment).isNotNull();
    }

    @ParameterizedTest
    @EnumSource(value = SessionStatus.class, names = {"PREPARING", "IN_PROGRESS"})
    @DisplayName("모집상태가 닫힐 경우, 수강 신청은 불가합니다.")
    void closedRecruitmentStatus_cannotEnroll(SessionStatus sessionStatus) {
        PaymentPolicy paidPaymentPolicy = new PaidPaymentPolicy(800_000L, 1);
        Session paidSession = new SessionBuilder()
                .title("TDD, 클린코드 with Java 20기")
                .coverImages(coverImages)
                .duration(duration)
                .paymentPolicy(paidPaymentPolicy)
                .sessionStatus(sessionStatus)
                .recruitmentStatus(RecruitmentStatus.CLOSE)
                .build();

        assertThatIllegalStateException().isThrownBy(() ->
                        paidSession.enroll(JAVAJIGI, 800_000L)
        );
    }

    @Test
    @DisplayName("강의는 자동승인 강의와 선발 승인강의가 있다.")
    void sessionType() {
        // TODO : 유료/무료 상관없이 강의는 일반/선별 두가지가 있음. 두가지 모두 테스트되어야 함.
        Session paidSession = new SessionBuilder()
                .title("TDD, 클린코드 with Java 20기")
                .coverImages(coverImages)
                .duration(duration)
                .paymentPolicy(new PaidPaymentPolicy(800_000L, 1))
                .sessionStatus(SessionStatus.PREPARING)
                .recruitmentStatus(RecruitmentStatus.OPEN)
                .sessionType(SessionType.AUTO_APPROVAL)
                .build();

        assertThat(paidSession.isAutoApproval()).isTrue();
        assertThat(paidSession.isSelectiveApproval()).isFalse();

        Session selectionSession = new SessionBuilder()
                .title("TDD, 클린코드 with Java 20기")
                .coverImages(coverImages)
                .duration(duration)
                .paymentPolicy(new FreePaymentPolicy())
                .sessionStatus(SessionStatus.PREPARING)
                .recruitmentStatus(RecruitmentStatus.OPEN)
                .sessionType(SessionType.SELECTIVE_APPROVAL)
                .build();

        assertThat(selectionSession.isAutoApproval()).isFalse();
        assertThat(selectionSession.isSelectiveApproval()).isTrue();
    }

    @Test
    @DisplayName("자동승인 강의는 수강 신청을 하면 자동으로 수강 등록 상태가 된다.")
    public void autoApprovalSession_enroll() {
        Session paidSession = new SessionBuilder()
                .title("TDD, 클린코드 with Java 20기")
                .coverImages(coverImages)
                .duration(duration)
                .paymentPolicy(new PaidPaymentPolicy(800_000L, 1))
                .sessionStatus(SessionStatus.PREPARING)
                .recruitmentStatus(RecruitmentStatus.OPEN)
                .sessionType(SessionType.AUTO_APPROVAL)
                .build();

        Payment payment = paidSession.enroll(JAVAJIGI, 800_000L);

        assertThat(paidSession.getEnrolledStudents().count()).isEqualTo(1);
        assertThat(paidSession.getEnrolledStudents().getStudents().get(0).getEnrollmentStatus())
                .isEqualTo(EnrollmentStatus.APPROVED);
    }

    @Test
    @DisplayName("선별 강의는 수강 신청을 하면 대기상태가 된다.")
    public void selectiveApprovalSession_enroll() {
        Session paidSession = new SessionBuilder()
                .title("TDD, 클린코드 with Java 20기")
                .coverImages(coverImages)
                .duration(duration)
                .paymentPolicy(new PaidPaymentPolicy(800_000L, 1))
                .sessionStatus(SessionStatus.PREPARING)
                .recruitmentStatus(RecruitmentStatus.OPEN)
                .sessionType(SessionType.SELECTIVE_APPROVAL)
                .build();

        Payment payment = paidSession.enroll(JAVAJIGI, 800_000L);

        assertThat(paidSession.getEnrolledStudents().count()).isEqualTo(1);
        assertThat(paidSession.getEnrolledStudents().getStudents().get(0).getEnrollmentStatus())
                .isEqualTo(EnrollmentStatus.WAITING);

    }
}