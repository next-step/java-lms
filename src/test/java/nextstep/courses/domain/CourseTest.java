package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CourseTest {

    @Test
    @DisplayName("과정(Course)은 여러 개의 강의(Session)를 가질 수 있다.")
    void courseHaveSession() {
        Course course = new Course("title", 1L);
        Session session = SessionTest.createSession(SessionStatus.OPEN);
        course.addSession(session);
        assertThat(course.getSessions()).contains(session);
    }

}
