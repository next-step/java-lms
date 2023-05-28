package nextstep.courses.fixtures;

import nextstep.courses.domain.Course;

public class CourseFixtureBuilder {
    public String title = "객체지향 프로그래밍";
    public Long creatorId = 1L;

    public Course build() {
        return new Course(title, creatorId);
    }
}
