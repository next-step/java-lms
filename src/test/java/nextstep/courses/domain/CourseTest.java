package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CourseTest {
    @Test
    void hasPeriod() {
        Course course = new Course("Clean Code", 123L, 1);
        assertThat(course.getPeriod()).isNotNull();
    }

    @Test
    void hasSessions() {
        List<Session> sessions = new ArrayList<>();
        Course course = new Course("Clean Code", 123L, 1, sessions);
        assertThat(course.getSessions()).isNotNull();
    }
}
