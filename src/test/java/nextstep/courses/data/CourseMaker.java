package nextstep.courses.data;

import nextstep.courses.domain.Course;

public class CourseMaker {
    public static Course makeCourse() {
        return new Course("title", 1L);
    }
}
