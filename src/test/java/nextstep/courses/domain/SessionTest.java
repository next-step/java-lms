package nextstep.courses.domain;

import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionTest {

    CoverImage defaultCoverImage;
    EnrollmentManager wrongEnrollmentManager;
    EnrollmentManager defaultEnrollmentManager;
    SessionPeriod defaultSessionPeriod;

    @BeforeEach
    void setUp() {
        defaultCoverImage = new CoverImage(new ImageCapacity(1_000L), ImageType.GIF, new ImageDimension(300L, 200L));
        defaultSessionPeriod = new SessionPeriod(LocalDate.now(), LocalDate.now());
        wrongEnrollmentManager = new EnrollmentManager(0L, 100, SessionStatus.CLOSED);
        defaultEnrollmentManager = new EnrollmentManager(1000L, 100, SessionStatus.RECRUITING);
    }

    @Test
    @DisplayName("수강신청 조건에 맞지 않다면 예외 발생")
    void throw_exception_cannot_enroll() {
        assertThatThrownBy(() -> {
            Payment payment = new Payment("1234", 1L, 1L, 1000L);
            new Session(defaultCoverImage, wrongEnrollmentManager, defaultSessionPeriod).enroll(payment);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
