package nextstep.courses.domain;

import nextstep.common.domain.BaseDomain;
import nextstep.session.domain.Session;

import java.time.LocalDateTime;
import java.util.List;

public class Course extends BaseDomain {
    private Long id;
    private String title;

    private Long creatorId;

    private List<Session> sessions;

    public Course() {
    }

    public Course(String title, Long creatorId) {
        super();
        this.title = title;
        this.creatorId = creatorId;
    }

    public Course(Long id, String title, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        this.id = id;
        this.title = title;
        this.creatorId = creatorId;
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

    @Override
    public String toString() {
        return "Course{" +
                "title='" + title + '\'' +
                ", creatorId=" + creatorId +
                ", sessions=" + sessions +
                '}';
    }

    public void addSession(Session session) {
        sessions.add(session);
    }
}
