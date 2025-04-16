package nextstep.session.domain;

import nextstep.payments.domain.FreePaymentPolicy;
import nextstep.payments.domain.PaidPaymentPolicy;
import nextstep.payments.domain.Payment;
import nextstep.payments.domain.PaymentPolicy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static nextstep.users.domain.NsUserTest.JAVAJIGI;
import static org.assertj.core.api.Assertions.assertThat;

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
        Session paidSession = new Session.Builder()
                .title("TDD, 클린코드 with Java 20기")
                .coverImage(coverImage)
                .duration(duration)
                .paymentPolicy(paidPaymentPolicy)
                .enrolledStudents(new EnrolledStudents())
                .status(SessionStatus.RECRUITING)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        assertThat(paidSession.title()).isEqualTo("TDD, 클린코드 with Java 20기");
    }

    @Test
    @DisplayName("정상적인 무료 강의 생성")
    void createFreeSession_success() {
        PaymentPolicy freePaymentPolicy = new FreePaymentPolicy();
        Session freeSession = new Session.Builder()
                .title("무료 강의")
                .coverImage(coverImage)
                .duration(duration)
                .paymentPolicy(freePaymentPolicy)
                .enrolledStudents(new EnrolledStudents())
                .status(SessionStatus.RECRUITING)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        assertThat(freeSession.title()).isEqualTo("무료 강의");
    }

    @Test
    @DisplayName("유료 강의를 수강하면 Payment 객체를 반환한다.")
    void paidSession_enrollReturnPayment() {
        PaymentPolicy paidPaymentPolicy = new PaidPaymentPolicy( 800_000L, 1);
        Session paidSession = new Session.Builder()
                .title("TDD, 클린코드 with Java 20기")
                .coverImage(coverImage)
                .duration(duration)
                .paymentPolicy(paidPaymentPolicy)
                .status(SessionStatus.RECRUITING)
                .build();

        Payment payment = paidSession.enroll(JAVAJIGI, 800_000L);

        assertThat(payment).isNotNull();
    }
}