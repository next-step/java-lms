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
    private Session paidSession;
    private Session freeSession;

    @BeforeEach
    public void setUp() {
        coverImage = new CoverImage("cover.png", "png", 500_000, 600, 400);

        duration = new Duration(
                LocalDate.of(2025, 5, 1),
                LocalDate.of(2025, 5, 30));

        paidSession = new Session(
                "TDD, 클린코드 with Java 20기",
                coverImage,
                duration,

                new PaymentPolicy(PaymentType.PAID, 800_000L, 10),
                new EnrolledStudents(),
                SessionStatus.RECRUITING,
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        freeSession = new Session(
                "무료 강의",
                coverImage,
                duration,
                new PaymentPolicy(PaymentType.FREE, 0L, 0),
                new EnrolledStudents(),
                SessionStatus.RECRUITING,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

    @Test
    @DisplayName("정상적인 유료 강의 생성")
    void createPaidSession_success() {
        Session session = paidSession;

        assertThat(session.title()).isEqualTo("TDD, 클린코드 with Java 20기");
    }

    @Test
    @DisplayName("무료 강의는 최대 수강 인원이 0이어야 함")
    void freeLecture_enrollmentMustBeZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Session(
                    "무료 강의",
                    coverImage,
                    duration,
                    new PaymentPolicy(PaymentType.FREE, 0L, 1),
                    new EnrolledStudents(),
                    SessionStatus.RECRUITING,
                    LocalDateTime.now(),
                    LocalDateTime.now()
            );
        });
    }

    @Test
    @DisplayName("유료 강의는 최대 수강 인원이 1 이상이어야 함")
    void paidLecture_enrollmentMustBePositive() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Session(
                    "유료 강의",
                    coverImage,
                    duration,
                    new PaymentPolicy(PaymentType.PAID, 800_000L, 0),
                    new EnrolledStudents(),
                    SessionStatus.RECRUITING,
                    LocalDateTime.now(),
                    LocalDateTime.now()
            );
        });
    }

    @Test
    @DisplayName("무료 강의는 최대 수강 인원 제한이 없다.")
    void freeLecture_noLimit() {
        Session session = freeSession;

        assertThat(session.paymentPolicy().enrollmentLimit()).isEqualTo(0);
    }

    @Test
    @DisplayName("유료 강의는 최대 수강 인원을 초과할 수 없다")
    void paidLecture_enrollmentLimitExists() {
        final int ENROLLMENT_LIMIT = 1;
        EnrolledStudents enrolledStudents = new EnrolledStudents();
        enrolledStudents.add(SANJIGI);
        Session session = new Session(
                "TDD, 클린코드 with Java 20기",
                coverImage,
                duration,
                new PaymentPolicy(PaymentType.PAID, 800_000L, ENROLLMENT_LIMIT),
                enrolledStudents,
                SessionStatus.RECRUITING,
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        assertThrows(IllegalStateException.class, () -> {
            session.enroll(JAVAJIGI, 800_000L);
        });
        assertThat(session.paymentPolicy().enrollmentLimit()).isEqualTo(ENROLLMENT_LIMIT);
    }

    @Test
    @DisplayName("유료 강의를 수강하면 Payment 객체를 반환한다.")
    void paidLecture_enrollReturnPayment() {
        Session session = paidSession;

        Payment payment = session.enroll(JAVAJIGI, 800_000L);

        assertThat(payment).isNotNull();
    }
}