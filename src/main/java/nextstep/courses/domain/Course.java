package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Course extends BaseEntity {

    private final String title;
    private final Long creatorId;
    private final List<Session> sessions = new ArrayList<>();
    private long id;


    public Course(String title, Long creatorId, LocalDateTime createdAt) {
        this(1L, title, creatorId, createdAt, null);
    }

    public Course(String title, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this(1L, title, creatorId, createdAt, updatedAt);

    }

    public Course(long id, String title, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        this.id = id;
        this.title = title;
        this.creatorId = creatorId;

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

    public List<Session> sessions() {
        return Collections.unmodifiableList(sessions);
    }

    public long id() {
        return id;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id() +
                ", title='" + title + '\'' +
                ", creatorId=" + creatorId +
                ", sessions=" + sessions +
                ", createAt=" + createdAt() +
                ", updateAt=" + updatedAt() +
                '}';
    }
}
