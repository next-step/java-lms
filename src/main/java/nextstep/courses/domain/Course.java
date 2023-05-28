package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Course {
    private final Set<Session> sessions = new HashSet<>();
    private Long id;
    private String title;
    private Long creatorId;
    private AuditTimestamp auditTimestamp;

    public Course() {
    }

    public Course(String title, Long creatorId) {
        this(0L, title, creatorId, LocalDateTime.now(), null);
    }

    public Course(Long id, String title, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.creatorId = creatorId;
        this.auditTimestamp = new AuditTimestamp(createdAt, updatedAt);
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public LocalDateTime getCreatedAt() {
        return auditTimestamp.getCreatedAt();
    }

    public LocalDateTime getUpdatedAt() {
        return auditTimestamp.getUpdatedAt();
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", creatorId=" + creatorId +
                ", createdAt=" + getCreatedAt() +
                ", updatedAt=" + getUpdatedAt() +
                '}';
    }

    public void addSession(Session session) {
        if (isDuplicateSession(session)) {
            throw new IllegalArgumentException("이미 등록된 강의입니다.");
        }
        sessions.add(session);
        auditTimestamp.update();
    }

    private boolean isDuplicateSession(Session session) {
        return sessions.stream()
                .map(Session::getId)
                .anyMatch(id -> id.equals(session.getId()));
    }

    public Set<Session> getSessions() {
        return Collections.unmodifiableSet(sessions);
    }

    public void deleteSession(Session session) {
        sessions.remove(session);
        auditTimestamp.update();
    }
}
