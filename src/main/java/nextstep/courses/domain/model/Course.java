package nextstep.courses.domain.model;

import org.springframework.lang.NonNull;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static nextstep.courses.domain.model.Timestamped.toLocalDateTime;

public class Course extends BaseEntity {
    private String title;

    private Long creatorId;

    private final List<Session> sessions;

    public Course(String title, Long creatorId) {
        this(null, title, creatorId, LocalDateTime.now(), LocalDateTime.now());
    }

    public Course(Long id, String title, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this(id, title, creatorId, new ArrayList<>(), createdAt, updatedAt);
    }

    public Course(Long id, String title, Long creatorId, Timestamp createdAt, Timestamp updatedAt) {
        this(id, title, creatorId, new ArrayList<>(), toLocalDateTime(createdAt), toLocalDateTime(updatedAt));
    }

    public Course(Long id, String title, Long creatorId, @NonNull List<Session> sessions, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, createdAt, updatedAt);
        this.title = title;
        this.creatorId = creatorId;
        this.sessions = sessions;
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

    public List<Session> getSessions() {
        return Collections.unmodifiableList(sessions);
    }

    @Override
    public String toString() {
        return "Course{" + ", title='" + title + '\'' + ", creatorId=" + creatorId + '}';
    }
}
