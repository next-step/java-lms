package nextstep.courses.domain.code;

import nextstep.courses.domain.Amount;
import nextstep.courses.domain.Students;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class EnrollmentTypeTest {

    @Test
    @DisplayName("유료 강의 신청을 한다")
    void apply2() {
        EnrollmentType actual = EnrollmentType.PAID;

        assertDoesNotThrow(() -> actual.validateEnroll(20000L, new Amount(20000L), 1,
                new Students()));
    }

    @Test
    @DisplayName("무료 강의 신청을 한다")
    void apply3() {
        EnrollmentType actual = EnrollmentType.FREE;

        assertDoesNotThrow(() -> actual.validateEnroll(0L, new Amount(20000L), 0, new Students()));
    }
}
