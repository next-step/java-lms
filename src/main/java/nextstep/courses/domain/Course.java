package nextstep.courses.domain;

import java.time.LocalDateTime;
import nextstep.core.domain.SoftDeletableBaseEntity;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.Sessions;

public class Course extends SoftDeletableBaseEntity {
    private final Sessions sessions = new Sessions();
    private String title;
    private Long creatorId;

    public Course() {
    }

    public Course(String title, Long creatorId) {
        this(0L, title, creatorId, LocalDateTime.now(), null);
    }

    public Course(Long id, String title, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, createdAt, updatedAt);
        this.title = title;
        this.creatorId = creatorId;
    }

    public String getTitle() {
        return title;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void addSession(Session session) {
        sessions.add(session);
    }

    public int sessionCount() {
        return sessions.size();
    }

    @Override
    public String toString() {
        return "Course{" +
            "id=" + getId() +
            ", title='" + title + '\'' +
            ", creatorId=" + creatorId +
            ", createdAt=" + getCreatedAt() +
            ", updatedAt=" + getUpdatedAt() +
            '}';
    }
}
