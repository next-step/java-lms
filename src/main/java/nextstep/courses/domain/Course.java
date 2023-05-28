package nextstep.courses.domain;

import java.time.LocalDateTime;

public class Course extends BaseEntity {
    private String title;

    private Long creatorId;

    private Sessions sessions;

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

    public void registerSession(Long sessionId, int studentsCount) {
        this.sessions.registerSession(sessionId, studentsCount);
    }

    public Sessions getSessions() {
        return this.sessions;
    }

    public String getTitle() {
        return this.title;
    }

    public Long getCreatorId() {
        return this.creatorId;
    }

    public LocalDateTime getCreatedAt() {
        return super.getCreatedAt();
    }

}
