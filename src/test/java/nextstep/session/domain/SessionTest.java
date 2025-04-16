package nextstep.session.domain;

import nextstep.payments.domain.Payment;
import nextstep.payments.domain.PaymentPolicy;
import nextstep.payments.domain.PaymentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static nextstep.users.domain.NsUserTest.JAVAJIGI;
import static nextstep.users.domain.NsUserTest.SANJIGI;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SessionTest {

    private CoverImage coverImage;
    private Duration duration;
    private PaymentPolicy paidPaymentPolicy;
    private PaymentPolicy freePaymentPolicy;

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

        paidPaymentPolicy = new PaymentPolicy(PaymentType.PAID, 800_000L, 1);
        freePaymentPolicy = new PaymentPolicy(PaymentType.FREE, 0L, 0);
    }

    @Test
    @DisplayName("정상적인 유료 강의 생성")
    void createPaidSession_success() {
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
        assertThat(paidSession.paymentPolicy().isPaidPayment()).isTrue();
    }

    @Test
    @DisplayName("정상적인 무료 강의 생성")
    void createFreeSession_success() {
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
        assertThat(freeSession.paymentPolicy().isFreePayment()).isTrue();
    }

    @Test
    @DisplayName("무료 강의는 최대 수강 인원이 0이어야 함")
    void freeSession_enrollmentMustBeZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            new PaymentPolicy(PaymentType.FREE, 0L, 1);
        });
    }

    @Test
    @DisplayName("유료 강의는 최대 수강 인원을 초과할 수 없다")
    void paidSession_enrollmentLimitExists() {
        final int ENROLLMENT_LIMIT = 1;
        EnrolledStudents enrolledStudents = new EnrolledStudents();
        enrolledStudents.add(SANJIGI);
        Session paidSession = new Session.Builder()
                .title("TDD, 클린코드 with Java 20기")
                .coverImage(coverImage)
                .duration(duration)
                .paymentPolicy(paidPaymentPolicy)
                .enrolledStudents(enrolledStudents)
                .status(SessionStatus.RECRUITING)
                .build();

        assertThrows(IllegalStateException.class, () -> {
            paidSession.enroll(JAVAJIGI, 800_000L);
        });
        assertThat(paidSession.paymentPolicy().enrollmentLimit()).isEqualTo(ENROLLMENT_LIMIT);
    }

    @Test
    @DisplayName("유료 강의를 수강하면 Payment 객체를 반환한다.")
    void paidSession_enrollReturnPayment() {
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