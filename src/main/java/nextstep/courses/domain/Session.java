package nextstep.courses.domain;

import nextstep.courses.domain.session.CoverImage;
import nextstep.courses.domain.session.DateRange;
import nextstep.courses.domain.session.Status;

import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Session {
    private final long id;
    private final DateRange dateRange;
    private final CoverImage coverImage;
    private final Status status;
    private Long creatorId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Session(long id,
                   DateRange dateRange,
                   CoverImage coverImage,
                   Status status,
                   Long creatorId,
                   LocalDateTime createdAt,
                   LocalDateTime updatedAt) {
        this.id = id;
        this.dateRange = dateRange;
        this.coverImage = coverImage;
        this.status = status;
        this.creatorId = creatorId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return id == session.id && Objects.equals(dateRange, session.dateRange) && Objects.equals(coverImage, session.coverImage) && status == session.status && Objects.equals(creatorId, session.creatorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateRange, coverImage, status, creatorId);
    }
}
