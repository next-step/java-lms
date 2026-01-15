package nextstep.courses.domain.image;

import java.time.LocalDateTime;
import nextstep.core.domain.BaseEntity;

public class CoverImage extends BaseEntity {
    private final Long sessionId;
    private final Long size;
    private final ImageType type;
    private final int width;
    private final int height;

    public CoverImage(Long sessionId, long size, ImageType type, int width, int height) {
        this(null, sessionId, size, type, width, height, LocalDateTime.now(), null);
        validateSize(size);
        validateDimension(width, height);
        validateRatio(width, height);
    }

    public CoverImage(Long id, Long sessionId, long size, ImageType type, int width, int height,
        LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, createdAt, updatedAt);
        this.sessionId = sessionId;
        this.size = size;
        this.type = type;
        this.width = width;
        this.height = height;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public Long getSize() {
        return size;
    }

    public ImageType getType() {
        return type;
    }

    public String getTypeName() {
        return type.name();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    private void validateRatio(int width, int height) {
        if (width * 2 != height * 3) {
            throw new IllegalArgumentException();
        }
    }

    private void validateDimension(int width, int height) {
        if (width < 300 || height < 200) {
            throw new IllegalArgumentException();
        }
    }

    private void validateSize(long size) {
        if (size > 1_048_576L) {
            throw new IllegalArgumentException();
        }
    }
}
