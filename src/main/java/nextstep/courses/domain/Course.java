package nextstep.courses.domain;

import java.time.LocalDateTime;
import nextstep.Session.Session;
import nextstep.Session.Sessions;

public class Course {

    private Long id;

    private String title;

    private Long creatorId;

    private Sessions sessions = new Sessions();

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Course() {
    }

    public Course(String title, Long creatorId) {
        this(0L, title, creatorId, LocalDateTime.now(), null);
    }

    public Course(Long id, String title, Long creatorId, LocalDateTime createdAt,
        LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.creatorId = creatorId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public Sessions getSessions() {
        return sessions;
    }

    public void addSession(final Session session) {
        sessions.add(session);
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
