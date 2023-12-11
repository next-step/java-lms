package nextstep.courses.domain.course;

import java.time.LocalDateTime;
import nextstep.courses.domain.session.Sessions;

public class Course {

    private final Long id;
    private final String title;
    private final Long creatorId;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private Sessions sessions;

    public Course(String title, Long creatorId) {
        this(null, title, creatorId, LocalDateTime.now(), null, null);
    }

    public Course(Long id, String title, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this(id, title, creatorId, createdAt, updatedAt, null);
    }

    public Course(Long id, String title, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt,
        Sessions sessions) {
        this.id = id;
        this.title = title;
        this.creatorId = creatorId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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
}
