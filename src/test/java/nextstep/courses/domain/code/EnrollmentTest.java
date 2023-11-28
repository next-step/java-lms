package nextstep.courses.domain.code;

import nextstep.courses.domain.Amount;
import nextstep.courses.domain.Student;
import nextstep.courses.domain.Students;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class EnrollmentTest {

    @Test
    @DisplayName("유료 강의 신청을 한다")
    void apply2() {
        Enrollment actual = Enrollment.PAID;

        assertDoesNotThrow(() -> actual.enroll(20000L, new Amount(20000L), 1, new Student(), new Students()));
    }

    @Test
    @DisplayName("무료 강의 신청을 한다")
    void apply3() {
        Enrollment actual = Enrollment.FREE;

        assertDoesNotThrow(() -> actual.enroll(0L, new Amount(20000L), 0, new Student(), new Students()));
    }
}
