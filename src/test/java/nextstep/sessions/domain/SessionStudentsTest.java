package nextstep.sessions.domain;

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
        sessionStudents.addStudent(new SessionStudent(NsUserTest.JAVAJIGI, 0L));
    }

    @DisplayName("이미 등록된 수강신청자를 추가하면 IllegalException을 던진다.")
    @Test
    void addStudentExceptionTest() {
        assertThatThrownBy(() -> sessionStudents.addStudent(new SessionStudent(NsUserTest.JAVAJIGI, 0L)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("수강신청에 승인된 학생들의 수를 알 수 있다.")
    @Test
    void approvalStudentCountTest() {
        SessionStudent sessionStudent = new SessionStudent(NsUserTest.SANJIGI, 0L);
        sessionStudents.addStudent(sessionStudent);
        sessionStudents.approve(sessionStudent);

        assertThat(sessionStudents.approvalStudentCount()).isEqualTo(1);
    }
}
