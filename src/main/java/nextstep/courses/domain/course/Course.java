package nextstep.courses.domain.course;

import nextstep.courses.domain.session.Session;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Course {
    private Long id;

    private Integer generation;

    private String title;

    private Long creatorId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private List<Session> sessions = new ArrayList<>();

    public Course(String title, Long creatorId) {
        this(0L, 1, title, creatorId, LocalDateTime.now(), null);
    }

    public Course(final String title, final Integer generation, final Long creatorId) {
        this(0L, generation, title, creatorId, LocalDateTime.now(), null);
    }

    public Course(Long id, Integer generation, String title, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        validateCourse(id, generation, title, creatorId, createdAt);

        this.id = id;
        this.generation = generation;
        this.title = title;
        this.creatorId = creatorId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    private void validateCourse(final Long id, final Integer generation, final String title, final Long creatorId, final LocalDateTime createdAt) {
        Assert.notNull(id, "id must not be null");
        Assert.isTrue(generation > 0, "generation must be greater than 0");
        Assert.hasText(title, "title must not be empty");
        Assert.isTrue(creatorId > 0, "creatorId must be greater than 0");
        Assert.notNull(createdAt, "createdAt must not be null");
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

    public void addSession(final Session session) {
        Assert.notNull(session, "session must not be null");

        this.sessions.add(session);
    }

    public List<Session> getSessions() {
        return this.sessions;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Course course = (Course) o;
        return Objects.equals(generation, course.generation)
                && Objects.equals(getTitle(), course.getTitle())
                && Objects.equals(getCreatorId(), course.getCreatorId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(generation, getTitle(), getCreatorId());
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", generation=" + generation +
                ", title='" + title + '\'' +
                ", creatorId=" + creatorId +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
