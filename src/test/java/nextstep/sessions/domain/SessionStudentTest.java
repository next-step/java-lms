package nextstep.sessions.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SessionStudentTest {

    @DisplayName("유저와 강의 신청 시간을 전달하면 객체를 생성한다.")
    @Test
    void sessionStudentTest() {
        assertThat(new SessionStudent(NsUserTest.JAVAJIGI)).isInstanceOf(SessionStudent.class);
    }

    @DisplayName("NsUser가 동일하면 true를 반환한다.")
    @Test
    void equalsTrueTest() {
        SessionStudent student1 = new SessionStudent(NsUserTest.JAVAJIGI);
        SessionStudent student2 = new SessionStudent(NsUserTest.JAVAJIGI);

        assertThat(student1.equals(student2)).isTrue();
    }

    @DisplayName("NsUser가 동일하지 않으면 false를 반환한다.")
    @Test
    void equalsFalseTest() {
        SessionStudent student1 = new SessionStudent(NsUserTest.JAVAJIGI);
        SessionStudent student2 = new SessionStudent(NsUserTest.SANJIGI);

        assertThat(student1.equals(student2)).isFalse();
    }

    @DisplayName("승인된 학생은 true를 반환한다.")
    @Test
    void isApprovalTrueTest() {
        SessionStudent student = new SessionStudent(NsUserTest.JAVAJIGI);
        student.approve();

        assertThat(student.isApproval()).isTrue();
    }

    @DisplayName("승인되지 않았거나 대기중인 학생은 false를 반환한다.")
    @Test
    void isApprovalFalseTest() {
        SessionStudent student1 = new SessionStudent(NsUserTest.JAVAJIGI);
        SessionStudent student2 = new SessionStudent(NsUserTest.SANJIGI);
        student1.cancel();

        assertThat(student1.isApproval()).isFalse();
        assertThat(student2.isApproval()).isFalse();
    }
}
