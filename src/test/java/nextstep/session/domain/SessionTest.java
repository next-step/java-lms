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
import nextstep.session.support.SessionTestBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

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

        String title = "TDD, 클린코드 with Java 20기";
        Session paidSession = new SessionBuilder()
                .title(title)
                .coverImages(coverImages)
                .duration(duration)
                .paymentPolicy(paidPaymentPolicy)
                .enrolledStudents(new EnrolledStudents())
                .sessionStatus(SessionStatus.PREPARING)
                .recruitmentStatus(RecruitmentStatus.OPEN)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        assertThat(paidSession.title()).isEqualTo(title);
    }

    @Test
    @DisplayName("정상적인 무료 강의 생성")
    void createFreeSession_success() {
        PaymentPolicy freePaymentPolicy = new FreePaymentPolicy();
        String title = "무료 강의";
        Session freeSession = new SessionBuilder()
                .title(title)
                .coverImages(coverImages)
                .duration(duration)
                .paymentPolicy(freePaymentPolicy)
                .enrolledStudents(new EnrolledStudents())
                .sessionStatus(SessionStatus.PREPARING)
                .recruitmentStatus(RecruitmentStatus.OPEN)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        assertThat(freeSession.title()).isEqualTo(title);
    }

    @Test
    @DisplayName("유료 강의를 수강하면 Payment 객체를 반환한다.")
    void paidSession_enrollReturnPayment() {
        PaymentPolicy paidPaymentPolicy = new PaidPaymentPolicy( 800_000L, 1);

        Session paidSession = new SessionTestBuilder()
                .withPaymentPolicy(paidPaymentPolicy)
                .build();

        Payment payment = paidSession.enroll(JAVAJIGI, 800_000L);

        assertThat(payment).isNotNull();
    }

    @ParameterizedTest
    @EnumSource(value = SessionStatus.class, names = {"PREPARING", "IN_PROGRESS"})
    @DisplayName("강의가 모집중이거나 진행중일 때 수강신청이 가능해야 한다.")
    void preparingOrInProgressSessionStatus_canEnroll(SessionStatus sessionStatus) {
        PaymentPolicy paidPaymentPolicy = new PaidPaymentPolicy(800_000L, 1);
        Session session = new SessionTestBuilder()
                .withPaymentPolicy(paidPaymentPolicy)
                .withRecruitmentStatus(RecruitmentStatus.OPEN)
                .build();

        Payment payment = session.enroll(JAVAJIGI, 800_000L);

        assertThat(payment).isNotNull();
    }

    @ParameterizedTest
    @EnumSource(value = SessionStatus.class, names = {"PREPARING", "IN_PROGRESS"})
    @DisplayName("모집상태가 닫힐 경우, 수강 신청은 불가합니다.")
    void closedRecruitmentStatus_cannotEnroll(SessionStatus sessionStatus) {
        PaymentPolicy paidPaymentPolicy = new PaidPaymentPolicy(800_000L, 1);
        Session session = new SessionTestBuilder()
                .withPaymentPolicy(paidPaymentPolicy)
                .withSessionStatus(sessionStatus)
                .withRecruitmentStatus(RecruitmentStatus.CLOSE)
                .build();

        assertThatIllegalStateException().isThrownBy(() ->
                session.enroll(JAVAJIGI, 800_000L)
        );
    }

    @Test
    @DisplayName("자동승인 강의를 생성할 수 있다.")
    void canCreateAutoApprovalSession() {
        Session autoSession = new SessionTestBuilder()
                .withSessionType(SessionType.AUTO_APPROVAL)
                .build();

        assertThat(autoSession.isAutoApproval()).isTrue();
        assertThat(autoSession.isSelectiveApproval()).isFalse();
    }

    @Test
    @DisplayName("선발 승인강의를 설정할 수 있다.")
    void canCreateSetSelectiveSession() {
        Session selectiveSession = new SessionTestBuilder()
                .withSessionType(SessionType.SELECTIVE_APPROVAL)
                .build();

        assertThat(selectiveSession.isAutoApproval()).isFalse();
        assertThat(selectiveSession.isSelectiveApproval()).isTrue();
    }

    @Test
    @DisplayName("자동승인 강의는 수강 신청을 하면 자동으로 수강 등록 상태가 된다.")
    public void autoApprovalSession_enroll() {

        PaymentPolicy policy = new PaidPaymentPolicy(800_000L, 1);
        Session autoSession = new SessionTestBuilder()
                .withPaymentPolicy(policy)
                .withSessionType(SessionType.AUTO_APPROVAL)
                .withRecruitmentStatus(RecruitmentStatus.OPEN)
                .build();

        Payment payment = autoSession.enroll(JAVAJIGI, 800_000L);

        assertThat(payment).isNotNull();
        assertThat(autoSession.getEnrolledStudents().count()).isEqualTo(1);
        assertThat(autoSession.getEnrolledStudents().getStudents().get(0).getEnrollmentStatus())
                .isEqualTo(EnrollmentStatus.APPROVED);
    }

    @Test
    @DisplayName("선별 강의는 수강 신청을 하면 대기상태가 된다.")
    public void selectiveApprovalSession_enroll() {
        PaymentPolicy policy = new PaidPaymentPolicy(800_000L, 1);
        Session selectiveSession = new SessionTestBuilder()
                .withPaymentPolicy(policy)
                .withSessionType(SessionType.SELECTIVE_APPROVAL)
                .withRecruitmentStatus(RecruitmentStatus.OPEN)
                .build();

        Payment payment = selectiveSession.enroll(JAVAJIGI, 800_000L);

        assertThat(payment).isNotNull();
        assertThat(selectiveSession.getEnrolledStudents().count()).isEqualTo(1);
        assertThat(selectiveSession.getEnrolledStudents().getStudents().get(0).getEnrollmentStatus())
                .isEqualTo(EnrollmentStatus.WAITING);

    }
}