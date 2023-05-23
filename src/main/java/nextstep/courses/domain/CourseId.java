package nextstep.courses.domain;

import java.util.Objects;

public class CourseId {
    private Long courseId;

    public CourseId() {
    }

    public CourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getCourseId() {
        return courseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseId other = (CourseId) o;
        return this.hashCode() == other.hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId);
    }

    @Override
    public String toString() {
        return "CourseId{" +
                "courseId=" + courseId +
                '}';
    }
}
