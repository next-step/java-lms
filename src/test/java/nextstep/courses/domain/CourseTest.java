package nextstep.courses.domain;

import nextstep.courses.fixture.SessionFixture;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CourseTest {
    @Test
    @DisplayName("과정에 여러개의 강의(Session)를 추가할 수 있다")
    void addSession() {
        Course course = new Course("course", NsUserTest.JAVAJIGI.getId());
        course.addSession(SessionFixture.create(SessionStatus.RECRUITING, 1));
        course.addSession(SessionFixture.create(SessionStatus.PREPARING, 1));

        assertThat(course.sessionCount()).isEqualTo(2);
    }
}
