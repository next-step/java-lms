package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Course extends BaseEntity {
    private Long id;
    private String title;
    private Long creatorId;
    private List<Session> sessions = new ArrayList<>();


    public Course() {

    }

    public Course(String title, Long creatorId, LocalDateTime createdAt) {
        this(0L, title, creatorId, createdAt, null);
    }

    public Course(Long id, String title, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
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

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", creatorId=" + creatorId +
                '}';
    }
}
