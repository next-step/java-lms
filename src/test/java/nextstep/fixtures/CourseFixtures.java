package nextstep.fixtures;

import nextstep.courses.domain.Course;

public class CourseFixtures {
    public static Course testCourse1() {
        return new Course("newCourse",1L);
    }
}
