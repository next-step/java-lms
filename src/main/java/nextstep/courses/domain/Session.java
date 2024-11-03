package nextstep.courses.domain;

import nextstep.courses.domain.session.*;

import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Session {
    protected final long id;
    protected final long courseId;
    protected final Category category;
    protected final DateRange dateRange;
    protected final CoverImage coverImage;
    protected final Status status;
    protected final long creatorId;
    protected final LocalDateTime createdAt;
    protected LocalDateTime updatedAt;

    public Session(long id,
                   long courseId,
                   Category category,
                   DateRange dateRange,
                   CoverImage coverImage,
                   Status status,
                   long creatorId,
                   LocalDateTime createdAt,
                   LocalDateTime updatedAt) {
        this.id = id;
        this.category = category;
        this.courseId = courseId;
        this.dateRange = dateRange;
        this.coverImage = coverImage;
        this.status = status;
        this.creatorId = creatorId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public long getId() {
        return id;
    }

    public long getCourseId() {
        return courseId;
    }

    public Category getCategory() {
        return category;
    }

    public DateRange getDateRange() {
        return dateRange;
    }

    public CoverImage getCoverImage() {
        return coverImage;
    }

    public Status getStatus() {
        return status;
    }

    public long getCreatorId() {
        return creatorId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return id == session.id && courseId == session.courseId && creatorId == session.creatorId && Objects.equals(dateRange, session.dateRange) && Objects.equals(coverImage, session.coverImage) && status == session.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, courseId, dateRange, coverImage, status, creatorId);
    }
}
