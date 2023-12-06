package nextstep.courses.domain;

import java.time.LocalDateTime;

import nextstep.courses.domain.session.Sessions;

public class Course extends BaseTimeEntity{
    private final Long id;

    private final String title;

    private final Long creatorId;

    private Sessions sessions;

    public Course(String title, Long creatorId) {
        this(0L, title, creatorId, LocalDateTime.now(), null);
    }

    public Course(
        Long id, String title, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt
    ) {
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

    @Override
    public String toString() {
        return "Course{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", creatorId=" + creatorId +
            '}';
    }
}
