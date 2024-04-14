package nextstep.courses.domain.image;

public class SessionCoverImage {

    private final Long sessionId;
    private final ImageFileSize fileSize;
    private final ImageSize size;
    private final ImageExtension extension;

    public SessionCoverImage(Long sessionId, long size, int width, int height, String extension) {
        this(sessionId, new ImageFileSize(size), new ImageSize(width, height), ImageExtension.get(extension));
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
