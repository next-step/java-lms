package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import nextstep.courses.domain.session.Session;

public class Course {
    private Long id;

    private String title;

    private Long creatorId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Cohort cohort;

    private List<Session> sessions;

    public Course(long id, Cohort cohort, List<Session> sessions) {
        this.id = id;
        this.cohort = cohort;
        this.sessions = sessions;
    }

    public static Course of(long id, Cohort cohort, List<Session> sessions) {
        return new Course(id, cohort, sessions);
    }

    public Course(String title, Long creatorId) {
        this(0L, title, creatorId, LocalDateTime.now(), null, null, null);
    }

    public Course(Long id, String title, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt, Cohort cohort, List<Session> sessions) {
        this.id = id;
        this.title = title;
        this.creatorId = creatorId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.cohort = cohort;
        this.sessions = sessions;
    }

    public String getTitle() {
        return title;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", creatorId=" + creatorId +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Course course = (Course) o;
        return Objects.equals(id, course.id) && Objects.equals(title, course.title)
            && Objects.equals(creatorId, course.creatorId) && Objects.equals(
            createdAt, course.createdAt) && Objects.equals(updatedAt, course.updatedAt)
            && Objects.equals(cohort, course.cohort) && Objects.equals(sessions,
            course.sessions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, creatorId, createdAt, updatedAt, cohort, sessions);
    }
}
