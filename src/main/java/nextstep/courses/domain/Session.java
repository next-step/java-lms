package nextstep.courses.domain;

import nextstep.courses.domain.session.CoverImage;
import nextstep.courses.domain.session.DateRange;
import nextstep.courses.domain.session.Status;
import nextstep.courses.domain.session.Students;

import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Session {
    protected final long id;
    protected final long courseId;
    protected final DateRange dateRange;
    protected final CoverImage coverImage;
    protected final Status status;
    protected long creatorId;
    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;

    public Session(long id,
                   long courseId,
                   DateRange dateRange,
                   CoverImage coverImage,
                   Status status,
                   long creatorId,
                   LocalDateTime createdAt,
                   LocalDateTime updatedAt) {
        this.id = id;
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

    public Object[] toParameters() {
        return new Object[]{
                id,
                creatorId,
                courseId,
                dateRange.getStartAt(),
                dateRange.getEndAt(),
                coverImage.getImageFileSize().getSize(),
                coverImage.getImageType().name(),
                coverImage.getImageSize().getWidth(),
                coverImage.getImageSize().getHeight(),
                status.name(),
                createdAt
        };
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
