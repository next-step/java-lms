package nextstep.courses.infrastructure.entity;

import nextstep.courses.domain.model.Course;

public class JdbcCourse extends BaseEntity {
    private String title;
    private boolean hasSelection;
    private Long creatorId;

    protected JdbcCourse() {
        super();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isHasSelection() {
        return hasSelection;
    }

    public void setHasSelection(boolean hasSelection) {
        this.hasSelection = hasSelection;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Course toDomain() {
        return new Course(getId(), title, hasSelection, creatorId, getCreatedAt().toLocalDateTime(), getUpdatedAt().toLocalDateTime());
    }
}
