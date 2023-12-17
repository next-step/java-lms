package nextstep.sessions.domain;

import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionStudentsTest {

    static SessionStudents sessionStudents;

    @BeforeEach
    void setSessionStudents() {
        sessionStudents = new SessionStudents();
        sessionStudents.addStudent(new SessionStudent(NsUserTest.JAVAJIGI));
        sessionStudents.addStudent(new SessionStudent(NsUserTest.SANJIGI));
    }

    @DisplayName("수강신청자의 수를 확인할 수 있다.")
    @Test
    void sizeTest() {
        assertThat(sessionStudents.size()).isEqualTo(2);
    }

    @DisplayName("수강신청자를 받아 목록에 추가할 수 있다.")
    @Test
    void addStudentTest() {
        SessionStudent newStudent = new SessionStudent(new NsUser(3L, "test", "password", "name", "test@naver.com"));
        sessionStudents.addStudent(newStudent);
        assertThat(sessionStudents.size()).isEqualTo(3);
    }

    @DisplayName("이미 등록된 수강신청자를 추가하면 IllegalException을 던진다.")
    @Test
    void addStudentExceptionTest() {
        assertThatThrownBy(() -> sessionStudents.addStudent(new SessionStudent(NsUserTest.JAVAJIGI)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
