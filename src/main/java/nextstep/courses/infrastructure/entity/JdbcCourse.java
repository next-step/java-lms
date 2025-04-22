package nextstep.courses.infrastructure.entity;

import nextstep.courses.domain.model.Course;

import java.sql.Timestamp;

public class JdbcCourse extends BaseEntity {
    private String title;
    private Long creatorId;

    public JdbcCourse() {
        super();
    }

    public JdbcCourse(Long id, String title, Long creatorId, Timestamp createdAt, Timestamp updatedAt) {
        super(id, createdAt, updatedAt);
        this.creatorId = creatorId;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getCreatorId() {
        return creatorId;
    }
    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Course toDomain() {
        return new Course(getId(), title, creatorId, getCreatedAt(), getUpdatedAt());
    }

}
