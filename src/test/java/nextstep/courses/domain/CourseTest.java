package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CourseTest {

    @Test
    public void create() throws Exception {
        assertThat(new Course("강의1", 1L)).isNotNull()
                .isInstanceOf(Course.class);
    }

    @Test
    public void addSession() throws Exception {
        Course course = new Course("강의1", 1L);
        course.addSession(SessionTest.SESSION01);

        assertThat(course.size()).isEqualTo(1);
    }

}
