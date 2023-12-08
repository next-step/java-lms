package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Course {
    private Long id;

    private String title;

    private int generation;

    private List<Session> sessions = new ArrayList<>();

    private Long creatorId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Course() {
    }

    public Course(String title, Long creatorId) {
        this(0L, title, creatorId, LocalDateTime.now(), null);
    }

    public Course(Long id, String title, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.creatorId = creatorId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Course(Long id, String title, int generation, List<Session> sessions, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.generation = generation;
        this.sessions = sessions;
        this.creatorId = creatorId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void addSessions(List<Session> sessions) {
        this.sessions.addAll(sessions);
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return generation == course.generation && Objects.equals(id, course.id) && Objects.equals(title, course.title) && Objects.equals(sessions, course.sessions) && Objects.equals(creatorId, course.creatorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, generation, sessions, creatorId);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", generation=" + generation +
                ", sessions=" + sessions +
                ", creatorId=" + creatorId +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
