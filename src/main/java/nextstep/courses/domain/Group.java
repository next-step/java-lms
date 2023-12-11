package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.List;

public class Group {
    private Course course;
    private Long courseNumber;

    public Group(Course course, Long number) {
        this.course = course;
        this.courseNumber = number;
    }

    public Course getCourse() {
        return course;
    }
}
