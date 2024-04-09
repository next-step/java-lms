package nextstep.courses.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class CourseTest {

    @Test
    void 무료강의_생성() {
        Course course = new Course();
        LocalDateTime now = LocalDateTime.now();
        Session session = new Session(1L, "lms", SessionType.FREE, SessionState.RECRUITING, "test.jpg", now.plusDays(5), now.plusDays(30));
        Session session2 = new Session(2L, "lms2", SessionType.FREE, SessionState.RECRUITING, "test.jpg", now.plusDays(15), now.plusDays(35));
        course.register(session);
        course.register(session2);
    }
}
