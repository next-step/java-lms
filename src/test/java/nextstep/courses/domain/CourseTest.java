package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CourseTest {
    @Test
    void hasPeriod() {
        Course course = new Course("Clean Code", 123L, 1);
        assertThat(course.getPeriod()).isNotNull();
    }

    @Test
    void hasSessions() {
        Sessions sessions = Sessions.from();
        Course course = new Course("Clean Code", 123L, 1, sessions);
        assertThat(course.getSessions()).isNotNull();
    }
}
