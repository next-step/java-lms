package nextstep.sessions.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class SessionStudentsTest {

    public static final SessionStudents sessionStudents = new SessionStudents(new HashSet<>(Arrays.asList(SessionStudentTest.student1, SessionStudentTest.student2)), 4);

    @ParameterizedTest(name = "nsUserId를 이용하여 수강 중인 학생인지 확인")
    @ValueSource(longs = {1L, 2L})
    void test1(Long nsUserId) {
        assertTrue(sessionStudents.contains(nsUserId));
    }

    @Test
    @DisplayName(value = "중복 수강 신청할 경우 검사")
    void test2() {
        assertThatThrownBy(() -> {
            sessionStudents.enrollStudent(SessionStudentTest.student1);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName(value = "정원 수 초과 검사")
    void test3() {
        assertThatThrownBy(() -> {
            sessionStudents.enrollStudent(SessionStudentTest.student2);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
