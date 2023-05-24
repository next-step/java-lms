package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Course {
    private Long id;

    private String title;

    private Long creatorId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private final List<Session> sessions = new ArrayList<>();

    public Course() {
    }

    public Course(String title, Long creatorId) {
        this(0L, title, creatorId, LocalDateTime.now(), null);
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

    public Session getNThSession(int generation) {
        validateGeneration(generation);
        return sessions.get(generation - 1);
    }

    private void validateGeneration(int generation) {
        validateNegative(generation);
        if (this.sessions.size() < generation) {
            throw new IllegalArgumentException("해당 기수의 강의는 존재하지 않습니다.");
        }
    }

    private static void validateNegative(int generation) {
        if (generation <= 0) {
            throw new IllegalArgumentException("기수는 1 기수 이상부터 시작합니다.");
        }
    }

    public void addSession(Session session) {
        this.sessions.add(session);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", creatorId=" + creatorId +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", sessions=" + sessions +
                '}';
    }
}
