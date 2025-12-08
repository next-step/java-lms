package nextstep.courses.domain;

import nextstep.courses.domain.session.BaseEntity;
import nextstep.courses.domain.session.Session;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Course extends BaseEntity {

    private String title;

    private Long creatorId;

    private List<Session> sessions = new ArrayList<>();


    public Course(String title, Long creatorId) {
        this(0L, title, creatorId, LocalDateTime.now(), null);
    }

    public Course(Long id, String title, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, createdAt, updatedAt);
        this.title = title;
        this.creatorId = creatorId;
    }

    public void addSession(Session session) {
        this.sessions.add(session);
    }

    public String getTitle() {
        return title;
    }

    public Long getCreatorId() {
        return creatorId;
    }


    @Override
    public String toString() {
        return "Course{" +
                "  title='" + title + '\'' +
                ", creatorId=" + creatorId +
                '}';
    }
}
