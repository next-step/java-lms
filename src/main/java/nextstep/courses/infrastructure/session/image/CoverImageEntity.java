package nextstep.courses.infrastructure.session.image;

import java.time.LocalDateTime;

import nextstep.common.BaseDateTime;
import nextstep.courses.domain.session.image.CoverImage;
import nextstep.courses.domain.session.image.Dimensions;
import nextstep.courses.domain.session.image.Height;
import nextstep.courses.domain.session.image.ImageType;
import nextstep.courses.domain.session.image.Size;
import nextstep.courses.domain.session.image.Width;

public class CoverImageEntity extends BaseDateTime {

    private final Long id;
    private final String type;
    private final long size;
    private final int width;
    private final int height;
    private final Long sessionId;

    public CoverImageEntity(
            final String type,
            final long size,
            final int width,
            final int height,
            final Long sessionId
    ) {
        this(null, type, size, width, height, sessionId);
    }

    public CoverImageEntity(
            final Long id,
            final String type,
            final long size,
            final int width,
            final int height,
            final Long sessionId
    ) {
        this.id = id;
        this.type = type;
        this.size = size;
        this.width = width;
        this.height = height;
        this.sessionId = sessionId;
    }

    public CoverImageEntity(
            final Long id,
            final String type,
            final long size,
            final int width,
            final int height,
            final Long sessionId,
            final LocalDateTime createdAt,
            final LocalDateTime updatedAt
    ) {
        super(createdAt, updatedAt);

        this.id = id;
        this.type = type;
        this.size = size;
        this.width = width;
        this.height = height;
        this.sessionId = sessionId;
    }

    public Long id() {
        return this.id;
    }

    public String type() {
        return this.type;
    }

    public long size() {
        return this.size;
    }

    public int width() {
        return this.width;
    }

    public int height() {
        return this.height;
    }

    public Long sessionId() {
        return this.sessionId;
    }

    public CoverImage toDomain() {
        return new CoverImage(
                this.id,
                ImageType.from(this.type),
                new Size(this.size),
                new Dimensions(new Width(this.width), new Height(this.height)),
                createdAt(),
                updatedAt()
        );
    }

    public static CoverImageEntity fromDomain(final CoverImage coverImage, final Long sessionId) {
        return new CoverImageEntity(
                coverImage.id(),
                coverImage.typeName(),
                coverImage.size(),
                coverImage.width(),
                coverImage.height(),
                sessionId,
                coverImage.createdAt(),
                coverImage.updatedAt()
        );
    }
}
