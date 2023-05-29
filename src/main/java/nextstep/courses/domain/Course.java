package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class Course {
    private final Long id;

    private final String title;

    private final Long creatorId;

    private final LocalDateTime createdAt;

    private final LocalDateTime updatedAt;
    private final List<Session> sessions;

    public Course(String title,
                  Long creatorId) {
        this(0L, title, creatorId, LocalDateTime.now(), LocalDateTime.now(),Collections.emptyList());
    }

    public Course(Long id, String title, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt, List<Session> sessions) {
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
