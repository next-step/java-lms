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
    }

    @DisplayName("이미 등록된 수강신청자를 추가하면 IllegalException을 던진다.")
    @Test
    void addStudentExceptionTest() {
        assertThatThrownBy(() -> sessionStudents.addStudent(new SessionStudent(NsUserTest.JAVAJIGI)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("수강신청에 승인된 학생들의 수를 알 수 있다.")
    @Test
    void approvalStudentCountTest() {
        SessionStudent sessionStudent = new SessionStudent(NsUserTest.SANJIGI);
        sessionStudents.addStudent(sessionStudent);
        sessionStudents.approval(sessionStudent, NsUser.ADMIN_USER);

        assertThat(sessionStudents.approvalStudentCount()).isEqualTo(1);
    }

    @DisplayName("강사가 아닌 사용자가 수강신청 승인을 할 경우 IllegalStateException을 던진다.")
    @Test
    void approvalExceptionTest() {
        SessionStudent sessionStudent = new SessionStudent(NsUserTest.SANJIGI);
        sessionStudents.addStudent(sessionStudent);

        assertThatThrownBy(() -> sessionStudents.approval(sessionStudent, NsUserTest.JAVAJIGI))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("강사가 아닌 사용자가 수강신청 취소를 할 경우 IllegalStateException을 던진다.")
    @Test
    void cancelExceptionTest() {
        SessionStudent sessionStudent = new SessionStudent(NsUserTest.SANJIGI);
        sessionStudents.addStudent(sessionStudent);

        assertThatThrownBy(() -> sessionStudents.cancel(sessionStudent, NsUserTest.JAVAJIGI))
                .isInstanceOf(IllegalStateException.class);
    }
}
