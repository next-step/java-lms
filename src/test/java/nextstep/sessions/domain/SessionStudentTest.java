package nextstep.sessions.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class SessionStudentTest {
    public static final SessionStudent NONE = new SessionStudent(0, 0);
    public static final SessionStudent ONE = new SessionStudent(1000, 1);

    @DisplayName("강의 금액과 최대 인원 수를 전달하면 SessionStudent 객체를 생성한다.")
    @Test
    void sessionStudentTest(){
        assertThat(new SessionStudent(1000, 10)).isInstanceOf(SessionStudent.class);
        assertThat(new SessionStudent(0, 10)).isInstanceOf(SessionStudent.class);
    }

    @DisplayName("강의 금액이 0원 이상일 경우 수강 인원에 제한이 없으면 IllegalArgumentException을 던진다.")
    @Test
    void sessionStudentExceptionTest() {
        assertThatThrownBy(() -> new SessionStudent(1000, 0))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("모집 인원이 마감된 강의를 추가하면 IllegalStateException을 던진다.")
    @Test
    void addStudentTest() {
        SessionStudent sessionStudent = new SessionStudent(1000, 1);
        sessionStudent.addStudent();

        assertThatThrownBy(() -> sessionStudent.addStudent())
                .isInstanceOf(IllegalStateException.class);
    }
}
