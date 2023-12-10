package nextstep.courses.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class CourseTest {

    @Test
    void 과정은_기수_단위로_운영한다() {
        String title = "title";
        Long creatorId = 1L;
        int no = 1;
        Course course = new Course(0L, title, creatorId, LocalDateTime.now(), null, no);

        assertThat(course.getTitle()).isEqualTo(title);
        assertThat(course.getNo()).isEqualTo(no);
    }

    @Test
    void 과정은_여러개의_강의를_가질_수_있다() {
        String title = "title";
        Long creatorId = 1L;
        int no = 1;
        Course course = new Course(0L, title, creatorId, LocalDateTime.now(), null, no);
        List<Session> sessions = Arrays.asList(new Session(), new Session());
        course.addSession(sessions);

        assertThat(course.getSession().size()).isEqualTo(sessions.size());
    }
}
