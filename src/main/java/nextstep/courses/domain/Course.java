package nextstep.courses.domain;

import nextstep.sessions.domain.Session;
import nextstep.sessions.domain.Sessions;

import java.time.LocalDateTime;
import java.util.List;

public class Course {
    private Long id;

    private String title;

    private Long creatorId;

    private int cardinalNumber;

    private Sessions sessions;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Course() {
    }

    public Course(String title, Long creatorId) {
        this(0L, title, creatorId, 1, null, LocalDateTime.now(), null);
    }

    public Course(Long id, String title, Long creatorId, int cardinalNumber, Sessions sessions,
                  LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.creatorId = creatorId;
        this.cardinalNumber = cardinalNumber;
        this.sessions = sessions;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Course(Long id, String title, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
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

    public List<Session> getPossibleSessionList() {
        return this.sessions.getPossibleSessionList();
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", creatorId=" + creatorId +
                ", cardinalNumber=" + cardinalNumber +
                ", sessions=" + sessions +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
