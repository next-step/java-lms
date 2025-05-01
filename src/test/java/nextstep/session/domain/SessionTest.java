package nextstep.session.domain;

import nextstep.payments.domain.FreePaymentPolicy;
import nextstep.payments.domain.PaidPaymentPolicy;
import nextstep.payments.domain.Payment;
import nextstep.payments.domain.PaymentPolicy;
import nextstep.session.domain.image.CoverImage;
import nextstep.session.domain.session.*;
import nextstep.session.domain.student.EnrolledStudents;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static nextstep.users.domain.NsUserTest.JAVAJIGI;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

class SessionTest {

    private CoverImage coverImage;
    private Duration duration;

    @BeforeEach
    public void setUp() {
        coverImage = new CoverImage.Builder()
                .fileName("cover.png")
                .imageFormat("png")
                .fileSize(100_000L)
                .imageSize(300, 200)
                .build();
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
                .coverImage(coverImage)
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
                .coverImage(coverImage)
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
                .coverImage(coverImage)
                .duration(duration)
                .paymentPolicy(paidPaymentPolicy)
                .sessionStatus(SessionStatus.PREPARING)
                .recruitmentStatus(RecruitmentStatus.OPEN)
                .build();

        Payment payment = paidSession.enroll2(JAVAJIGI, 800_000L);

        assertThat(payment).isNotNull();
    }

    @ParameterizedTest
    @EnumSource(value = SessionStatus.class, names = {"PREPARING", "IN_PROGRESS"})
    @DisplayName("강의가 모집중이거나 진행중일 때 수강신청이 가능해야 한다.")
    void preparingOrInProgressSessionStatus_canEnroll(SessionStatus sessionStatus) {
        PaymentPolicy paidPaymentPolicy = new PaidPaymentPolicy(800_000L, 1);
        Session paidSession = new SessionBuilder()
                .title("TDD, 클린코드 with Java 20기")
                .coverImage(coverImage)
                .duration(duration)
                .paymentPolicy(paidPaymentPolicy)
                .sessionStatus(sessionStatus)
                .recruitmentStatus(RecruitmentStatus.OPEN)
                .build();

        Payment payment = paidSession.enroll2(JAVAJIGI, 800_000L);

        assertThat(payment).isNotNull();
    }

    @ParameterizedTest
    @EnumSource(value = SessionStatus.class, names = {"PREPARING", "IN_PROGRESS"})
    @DisplayName("모집상태가 닫힐 경우, 수강 신청은 불가합니다.")
    void closedRecruitmentStatus_cannotEnroll(SessionStatus sessionStatus) {
        PaymentPolicy paidPaymentPolicy = new PaidPaymentPolicy(800_000L, 1);
        Session paidSession = new SessionBuilder()
                .title("TDD, 클린코드 with Java 20기")
                .coverImage(coverImage)
                .duration(duration)
                .paymentPolicy(paidPaymentPolicy)
                .sessionStatus(sessionStatus)
                .recruitmentStatus(RecruitmentStatus.CLOSE)
                .build();

        assertThatIllegalStateException().isThrownBy(() ->
                        paidSession.enroll2(JAVAJIGI, 800_000L)
        );
    }
}