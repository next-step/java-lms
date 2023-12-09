package nextstep.courses.domain;

import nextstep.common.domain.BaseEntity;
import nextstep.courses.domain.session.Session;

import java.time.LocalDateTime;
import java.util.List;

public class Course extends BaseEntity {
    private Long id;

    private String title;

    private Long creatorId;
    private List<Session> sessions;

    public Course(String title, Long creatorId) {
        this(0L, title, creatorId, LocalDateTime.now(), null, null);
    }

    public Course(Long id, String title, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this(0L, title, creatorId, createdAt, updatedAt, null);
    }
    public Course(Long id, String title, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt, List<Session> sessions) {
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
