package nextstep.courses.domain.code;

import nextstep.courses.exception.SessionClosedException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EnrollmentStatusTest {

    @Test
    @DisplayName("모집중일때는 예외 처리 하지 않는다")
    void recruiting() {
        EnrollmentStatus actual = EnrollmentStatus.RECRUITING;

        assertDoesNotThrow(actual::validateEnroll);
    }

    @Test
    @DisplayName("비모집중일때는 예외 처리 한다")
    void close() {
        EnrollmentStatus actual = EnrollmentStatus.CLOSED;

        assertThrows(SessionClosedException.class, actual::validateEnroll, "모집 종료된 강의 입니다.");
    }
}
