package nextstep.courses.domain;

public class CourseName {

    private final String courseName;

    public CourseName(String courseName) {
        this.courseName = courseName;
    }

    public CourseName editCourseName(String courseName) {
        return new CourseName(courseName);
    }

    @Override
    public String toString() {
        return this.courseName;
    }
}
