package nextstep.courses.domain;

import java.util.Objects;

public class CourseUser {
    private Long id;
    private Long courseId;
    private Long userId;
    private boolean selected;

    public CourseUser(Long courseId, Long userId, boolean selected) {
        this(0L, courseId, userId, selected);
    }

    public CourseUser(Long id, Long courseId, Long userId, boolean selected) {
        this.id = id;
        this.courseId = courseId;
        this.userId = userId;
        this.selected = selected;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public boolean isSelected() {
        return selected;
    }

    public boolean isNotSelected() {
        return selected == false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseUser that = (CourseUser) o;
        return selected == that.selected && Objects.equals(id, that.id) && Objects.equals(courseId, that.courseId) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, courseId, userId, selected);
    }
}
