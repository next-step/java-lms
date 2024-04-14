package nextstep.courses.domain;

import nextstep.courses.domain.utils.TestSessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class CourseTest {

    private final Long sessionId = 1L;

    @Test
    @DisplayName("과정 생성 테스트")
    void testCourse() {
        Course course = new Course("test", 1L);
        course.addSession(TestSessionFactory.recruitStatusSession(sessionId));
        course.addSession(TestSessionFactory.recruitStatusSession2(sessionId));

        Sessions sessions = course.getSessions();
        assertThat(sessions.getSessions()).hasSize(2).contains(TestSessionFactory.recruitStatusSession(sessionId), TestSessionFactory.recruitStatusSession2(sessionId));
    }

    @Test
    @DisplayName("등록된 강의가 중복된 경우 에러 발생 테스트")
    void testDuplicateSession() {
        Course course = new Course("test", 1L);
        course.addSession(TestSessionFactory.recruitStatusSession(sessionId));
        assertThatIllegalArgumentException().isThrownBy(() ->course.addSession(TestSessionFactory.recruitStatusSession(sessionId)));


    }
}
