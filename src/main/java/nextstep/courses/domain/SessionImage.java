package nextstep.courses.domain;

public class SessionImage {
    private final Long id;
    private final SessionImageType sessionImageType;
    private final SessionImageSize sessionImageSize;
    private final String path;

    public SessionImage(Long id, String path, int width, int height, int fileSize) {
        this.sessionImageSize = new SessionImageSize(width, height, fileSize);
        this.sessionImageType = SessionImageType.findByFilePath(path);
        this.path = path;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getPath() {
        return path;
    }
}
