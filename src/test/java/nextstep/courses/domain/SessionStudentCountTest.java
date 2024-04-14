package nextstep.courses.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SessionStudentCountTest {
    @Test
    @DisplayName("초기 학생수 확인.")
    void check_student_count() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SessionStudentCount(6,5);
        });
    }

    @Test
    @DisplayName("학생수 증가시 max 확인.")
    void check_max_student_count() {
        SessionStudentCount sessionStudentCount = new SessionStudentCount(5,5);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            sessionStudentCount.addStudent();
        });
    }

}
