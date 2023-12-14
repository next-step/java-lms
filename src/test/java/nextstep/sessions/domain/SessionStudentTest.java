package nextstep.sessions.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

class SessionStudentTest {

    @DisplayName("유저와 강의 신청 시간을 전달하면 객체를 생성한다.")
    @Test
    void sessionStudentTest() {
        assertThat(new SessionStudent(NsUserTest.JAVAJIGI, LocalDateTime.now())).isInstanceOf(SessionStudent.class);
    }

    @DisplayName("NsUser가 동일하면 true를 반환한다.")
    @Test
    void equalsTrueTest() {
        SessionStudent student1 = new SessionStudent(NsUserTest.JAVAJIGI, LocalDateTime.of(2023, 12, 13, 12, 12, 12));
        SessionStudent student2 = new SessionStudent(NsUserTest.JAVAJIGI, LocalDateTime.now());

        assertThat(student1.equals(student2)).isTrue();
    }

    @DisplayName("NsUser가 동일하지 않으면 false를 반환한다.")
    @Test
    void equalsFalseTest() {
        SessionStudent student1 = new SessionStudent(NsUserTest.JAVAJIGI, LocalDateTime.now());
        SessionStudent student2 = new SessionStudent(NsUserTest.SANJIGI, LocalDateTime.now());

        assertThat(student1.equals(student2)).isFalse();
    }
}
