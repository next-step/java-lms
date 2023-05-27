package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Course extends BaseTime {

    private final Long id;

    private final String title;

    private final Long creatorId;

    private final Term term;

    private final List<Session> sessions = new ArrayList<>();

    public Course(String title, Term term, Long creatorId) {
        this(0L, title, term, creatorId, LocalDateTime.now(), null);
    }

    public Course(Long id, String title, Term term, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        this.id = id;
        this.title = title;
        this.term = term;
        this.creatorId = creatorId;
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

    public void addSession(Session session) {
        sessions.add(session);
    }

    public int sessionCount() {
        return sessions.size();
    }

    public String termValue() {
        return term.ternValue();
    }

    public List<Session> fetchSessions() {
        return Collections.unmodifiableList(sessions);
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
