package nextstep.courses.domain;

import nextstep.utils.DomainId;

import java.util.Objects;

public class CourseId implements DomainId {
    private Long courseId;

    public CourseId(Long courseId) {
        this.courseId = courseId;
    }

    @Override
    public Long value() {
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
