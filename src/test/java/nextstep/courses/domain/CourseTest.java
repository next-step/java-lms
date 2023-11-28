package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CourseTest {

    @Test
    @DisplayName("과정에 세션을 추가할 수 있다")
    public void add_session() {
        Duration duration = new Duration(LocalDate.now(), LocalDate.now());
        Image image = new Image(1, "JPG", 300, 200);
        Session session = new FreeSession(duration, image);
        Course course = new Course("TDD, 클린 코드 with Java", 1L);

        course.addSession(session);
        assertThat(course.sessions()).isEqualTo(Arrays.asList(session));
    }

}
