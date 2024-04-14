package nextstep.courses.domain;

import nextstep.courses.domain.utils.TestSessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CourseTest {

    private final Long sessionId1 = 1L;
    private final Long sessionId2 = 2L;

    @Test
    @DisplayName("과정 생성 테스트")
    void testCourse() {
        Course course = new Course("test", 1L);
        course.addSession(TestSessionFactory.of(sessionId1));
        course.addSession(TestSessionFactory.of(sessionId2));

        Sessions sessions = course.getSessions();
        assertThat(sessions.getSessions()).hasSize(2).containsExactly(TestSessionFactory.of(sessionId1), TestSessionFactory.of(sessionId2));
    }
}
