package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CourseTest {

    @Test
    void 코스는_강의를_등록할_수_있다() {
        Course course = new Course(1);
        Session session = new Session();
        assertThat(course.register(session)).contains(session);
    }
}
