package nextstep.courses.domain;

import nextstep.common.domain.BaseDomain;
import nextstep.session.domain.Session;

import java.time.LocalDateTime;
import java.util.List;

public class Course extends BaseDomain {
    private String title;

    private Long creatorId;

    private List<Session> sessions;

    public Course() {
    }

    public Course(String title, Long creatorId) {
        this(0L, title, creatorId, LocalDateTime.now(), null);
    }

    public Course(Long id, String title, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, createdAt, updatedAt);
        this.title = title;
        this.creatorId = creatorId;
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
