package nextstep.courses.domain.course;

import java.time.LocalDateTime;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.Sessions;

public class Course {
    private Long id;

    private String title;

    private Long creatorId;

    private Sessions sessions;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Course() {
    }

    public Course(String title, Long creatorId) {
        this(0L, title, creatorId, new Sessions(), LocalDateTime.now(), null);
    }

    public Course(
            Long id, String title, Long creatorId, Sessions sessions,
            LocalDateTime createdAt, LocalDateTime updatedAt
    ) {
        this.id = id;
        this.title = title;
        this.creatorId = creatorId;
        this.sessions = sessions;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void addSession(Session session) {
        sessions.add(session);
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

    public int sessionCount() {
        return this.sessions.size();
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
