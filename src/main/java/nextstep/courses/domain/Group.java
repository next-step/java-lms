package nextstep.courses.domain;

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
