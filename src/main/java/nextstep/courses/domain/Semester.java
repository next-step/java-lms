package nextstep.courses.domain;

public class Semester extends BaseEntity{
    private final Course course;

    public Semester(Course course) {
        this.course = course;
    }
}
