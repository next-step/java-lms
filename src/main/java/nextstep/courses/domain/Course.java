package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Course {
    private Long id;

    /* 기수 */
    private String term;

    private String title;

    private final List<Session> sessions = new ArrayList<>();

    private Long creatorId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public static Course of(Long id, String term, String title, List<Session> sessions, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        return new Course(id, term, title, sessions, creatorId, createdAt, updatedAt);
    }

    public Course() {
    }

    public Course(String term, String title, Long creatorId) {
        this(0L, term, title, List.of(), creatorId, LocalDateTime.now(), LocalDateTime.now());
    }

    public Course(Long id, String term, String title, List<Session> sessions, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.term = term;
        this.title = title;
        this.sessions.addAll(sessions);
        this.creatorId = creatorId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String term() {
        return this.term;
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
}
