package nextstep.courses.fixture;

import nextstep.courses.domain.course.Course;
import nextstep.courses.domain.course.session.Sessions;

import java.time.LocalDateTime;

public class CourseFixtures {
    public static Course course() {
        return new Course(1L, "math", 1, new Sessions(), 1L, LocalDateTime.now(), null);
    }
}
