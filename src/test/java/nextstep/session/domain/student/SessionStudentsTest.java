package nextstep.session.domain.student;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class SessionStudentsTest {

    public static final SessionStudents sessionStudents = new SessionStudents(1);

    @BeforeAll
    static void setUp() {
        sessionStudents.enrollStudent(SessionStudentTest.STUDENT_APPROVE);
    }

    @Test
    @DisplayName(value = "nsUserId를 이용하여 수강 중인 학생인지 확인")
    void test1() {
        assertTrue(sessionStudents.contains(SessionStudentTest.STUDENT_APPROVE));
    }

    @Test
    @DisplayName(value = "중복 수강 신청할 경우 검사")
    void test2() {
        assertThatThrownBy(() -> {
            sessionStudents.enrollStudent(SessionStudentTest.STUDENT_APPROVE);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName(value = "정원 수 초과 검사")
    void test3() {
        assertThatThrownBy(() -> {
            sessionStudents.enrollStudent(SessionStudentTest.STUDENT_APPROVE);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
