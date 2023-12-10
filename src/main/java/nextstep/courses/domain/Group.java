package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.List;

public class Group {
    private Course course;
    private Long number;

    private List<NsUser> groupMember;

    public Group(Course course, Long number) {
        this.course = course;
        this.number = number;
    }

    public Course getCourse() {
        return course;
    }
}
