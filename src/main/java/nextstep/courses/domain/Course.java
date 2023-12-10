package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Course extends BaseEntity {
    private Long id;

    private String title;

    private Long creatorId;

    private final Sessions sessions = Sessions.of(new ArrayList<>());

    private Course() {
    }

    private Course(String title, Long creatorId) {
        this(0L, title, creatorId, LocalDateTime.now(), null);
    }

    private Course(Long id, String title, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        this.id = id;
        this.title = title;
        this.creatorId = creatorId;
    }

    public static Course of(String title, Long creatorId) {
        return new Course(title, creatorId);
    }

    public static Course of(Long id, String title, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        return new Course(id, title, creatorId, createdAt, updatedAt);
    }

    public void addSession(Session session) {
        this.sessions.add(session);
    }

    public Sessions getSessions() {
        return this.sessions;
    }

    public String getTitle() {
        return title;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", creatorId=" + creatorId +
                ", createdAt=" + super.getCreatedAt() +
                ", updatedAt=" + super.getUpdatedAt() +
                '}';
    }
}
