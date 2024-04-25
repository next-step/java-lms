package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import org.assertj.core.api.Assertions;
import org.assertj.core.internal.bytebuddy.asm.Advice.Local;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CourseTest {

    Image image;

    @BeforeEach
    void setUp() {
        image = new Image("test.png", 300, 200, 1_000);
    }

    @Test
    void 무료강의_생성() {
        Course course = new Course();
        LocalDateTime now = LocalDateTime.now();
        Session session = new Session(1L, "lms", SessionType.FREE, SessionState.RECRUITING, image, now.plusDays(5), now.plusDays(30));
        Session session2 = new Session(2L, "lms2", SessionType.FREE, SessionState.RECRUITING, image, now.plusDays(15), now.plusDays(35));
        course.add(session);
        course.add(session2);

        assertThat(course.countOfSession()).isEqualTo(2);
    }

    @Test
    void 유료강의_생성() {
        Course course = new Course();
        LocalDateTime now = LocalDateTime.now();
        Session session = new Session(1L, "lms", SessionType.PAID, SessionState.RECRUITING, image, now.plusDays(5), now.plusDays(30), 2, 5_000);
        course.add(session);
    }
}
