package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static nextstep.courses.domain.SessionBuilder.session;
import static org.assertj.core.api.Assertions.assertThat;

public class CourseTest {

    @Test
    void 코스는_여러개의_강의() {

        Course course = new Course(
                0L,
                "제목",
                1L,
                LocalDateTime.now(),
                null,
                List.of(
                        session().build(),
                        session().build()
                )
        );

        assertThat(course.getSessions()).hasSize(2);
    }
}
