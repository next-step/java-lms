package nextstep.courses.domain;

public class Group {
    private Course course;
    private Long number;

    public Group(Course course, Long number) {
        this.course = course;
        this.number = number;
    }

    public Course getCourse() {
        return course;
    }
}
