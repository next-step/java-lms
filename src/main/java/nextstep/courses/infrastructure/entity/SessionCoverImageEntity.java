package nextstep.courses.infrastructure.entity;

import nextstep.courses.domain.image.SessionCoverImage;

import java.time.LocalDateTime;

public class SessionCoverImageEntity extends BaseEntity {

    private long id;
    private final long sessionId;
    private final long size;
    private final int width;
    private final int height;
    private final String extension;

    public SessionCoverImageEntity(long id, long sessionId, long size, int width, int height, String extension, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        this.id = id;
        this.sessionId = sessionId;
        this.size = size;
        this.width = width;
        this.height = height;
        this.extension = extension;
    }

    public SessionCoverImageEntity(SessionCoverImage image, LocalDateTime createdAt) {
        super(createdAt);
        this.sessionId = image.getSessionId();
        this.size = image.getFileSize().get();
        this.width = image.getSize().getWidth();
        this.height = image.getSize().getHeight();
        this.extension = image.getExtension().value();
    }

    public long getSessionId() {
        return sessionId;
    }

    public long getSize() {
        return size;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getExtension() {
        return extension;
    }
}
