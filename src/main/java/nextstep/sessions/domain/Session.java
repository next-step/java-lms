package nextstep.sessions.domain;

import nextstep.courses.domain.Course;

public class Session {
    private Long id;

    private Course course;

    public static Session of(Long id, Course course) {
        return new Session(id, course);
    }

    private Session(Long id, Course course) {
        this.id = id;
        this.course = course;
    }

    public void toCourse(Course course) {
        this.course = course;
    }
}
