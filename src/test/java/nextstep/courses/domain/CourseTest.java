package nextstep.courses.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.time.LocalDateTime;
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
}
