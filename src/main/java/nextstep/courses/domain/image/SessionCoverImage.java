package nextstep.courses.domain.image;

import nextstep.courses.infrastructure.entity.BaseEntity;

import java.time.LocalDateTime;

public class SessionCoverImage extends BaseEntity {

    private Long id;

    private final Long sessionId;
    private final ImageFileSize fileSize;
    private final ImageSize size;
    private final ImageExtension extension;

    public SessionCoverImage(Long id, Long sessionId, long size, int width, int height, String extension, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this(id, sessionId, new ImageFileSize(size), new ImageSize(width, height), ImageExtension.get(extension), createdAt, updatedAt);
    }

    public SessionCoverImage(Long sessionId, long size, int width, int height, String extension) {
        this(sessionId, new ImageFileSize(size), new ImageSize(width, height), ImageExtension.get(extension));
    }

    private SessionCoverImage(Long id, Long sessionId, ImageFileSize fileSize, ImageSize size, ImageExtension extension, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        this.id = id;
        this.sessionId = sessionId;
        this.fileSize = fileSize;
        this.size = size;
        this.extension = extension;
    }

    private SessionCoverImage(Long sessionId, ImageFileSize fileSize, ImageSize size, ImageExtension extension) {
        this.sessionId = sessionId;
        this.fileSize = fileSize;
        this.size = size;
        this.extension = extension;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public ImageFileSize getFileSize() {
        return fileSize;
    }

    public ImageSize getSize() {
        return size;
    }

    public ImageExtension getExtension() {
        return extension;
    }

}
