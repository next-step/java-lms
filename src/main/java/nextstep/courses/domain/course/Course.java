package nextstep.courses.domain.course;

import nextstep.courses.domain.BaseEntity;
import nextstep.courses.domain.session.Sessions;

import java.time.LocalDateTime;

public class Course extends BaseEntity {
    private String title;
    private Long creatorId;
    private Sessions sessions;

    public Course(String title, Long creatorId) {
        this(0L, title, creatorId, LocalDateTime.now(), null);
    }

    public Course(Long id, String title, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this(id, title, creatorId, LocalDateTime.now(), null, null);
    }

    public Course(Long id, String title, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt, Sessions sessions) {
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
