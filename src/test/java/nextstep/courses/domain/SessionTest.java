package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SessionTest {


    @Test
    void assertSessionRegister() {
        Course course = new Course("test", 1L);
        Session session = new Session(1L);
        session.register(course);

        assertThat(course.sessionList().size()).isEqualTo(1);
    }
}