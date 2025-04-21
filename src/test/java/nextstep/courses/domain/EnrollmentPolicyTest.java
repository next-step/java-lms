package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import nextstep.courses.CannotEnrollException;

public class EnrollmentPolicyTest {
    Period period;

    @BeforeEach
    public void setUp() {
        period = new Period(LocalDate.now(), LocalDate.now().plusDays(1));
    }

    @Test
    @DisplayName("무료 강의는 최대 수강 인원 제한이 없다.")
    void remainingSeatOfFreeSession() {
        EnrollmentPolicy enrollmentPolicy = EnrollmentPolicy.free();
        assertThat(enrollmentPolicy.remainingSeats(0)).isEmpty();
        assertThat(enrollmentPolicy.remainingSeats(1)).isEmpty();
        assertThat(enrollmentPolicy.canEnroll(10)).isTrue();
    }

    @Test
    @DisplayName("유료 강의는 강의 수강 인원을 초과할 수 없다.")
    void overflowedSeatOfPaidSession() {
        EnrollmentPolicy enrollmentPolicy = EnrollmentPolicy.paid(10_000, 2);
        long enrolledCount = 2l;
        assertFalse(enrollmentPolicy.canEnroll(enrolledCount));
        assertThatThrownBy(() -> enrollmentPolicy.validateEnrollment(enrolledCount))
            .isInstanceOf(CannotEnrollException.class);
    }
}
