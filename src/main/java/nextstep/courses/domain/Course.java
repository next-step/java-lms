package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Course extends BaseTimeEntity{
    private final Long id;

    private final String title;

    private final Long creatorId;

    private final String generation;

    private final List<Session> sessions = new ArrayList<>();

    public Course(String title, Long creatorId, String generation) {
        this(0L, title, creatorId, generation, LocalDateTime.now(), null);
    }

    public Course(Long id, String title, Long creatorId, String generation, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        this.id = id;
        this.title = title;
        this.creatorId = creatorId;
        this.generation = generation;
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

    public String getGeneration() {
        return generation;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", creatorId=" + creatorId +
                ", generation='" + generation + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    public void addSession(Session session) {
        sessions.add(session);
    }

    public int getSessionsSize() {
        return sessions.size();
    }
}
