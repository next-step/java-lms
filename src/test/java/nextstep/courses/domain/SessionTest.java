package nextstep.courses.domain;

import nextstep.courses.CannotEnrollSessionException;
import nextstep.payments.domain.PaidEnrollmentPolicy;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static nextstep.payments.PaymentTest.PAYMENT_1000;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class SessionTest {

    public static final Session ReadySession = new Session(
            LocalDate.of(2025, 1, 1),
            LocalDate.of(2025, 12, 31),
            new CoverImage(600, 400, 500_000, ImageExtension.JPG),
            SessionStatus.READY,
            new PaidEnrollmentPolicy(100, 1000));

    public static final Session EnrollingSession = new Session(
            LocalDate.of(2025, 1, 1),
            LocalDate.of(2025, 12, 31),
            new CoverImage(600, 400, 500_000, ImageExtension.JPG),
            SessionStatus.ENROLLING,
            new PaidEnrollmentPolicy(100, 1000));

    @Test
    void 모집중이_아닐_때_수강신청_불가능() {
        assertThatThrownBy(() ->
                ReadySession.enroll(PAYMENT_1000)
        ).isInstanceOf(CannotEnrollSessionException.class);
    }

    @Test
    void 모집중일_때_수강신청_가능() {
        assertThatCode(() -> EnrollingSession.enroll(PAYMENT_1000))
                .doesNotThrowAnyException();

    }
}
