package nextstep.courses.domain;

public class Thumbnail {

    private final long id;
    private final long sessionId;
    private final String name;
    private final String uri;
    private final FileSize fileSize;
    private final ImageSize imageSize;

    public Thumbnail(long id,
                     long sessionId,
                     String name,
                     String uri,
                     long fileSize,
                     long widthPixel,
                     long heightPixel) {
        this(id, sessionId, name, uri, new FileSize(fileSize), new ImageSize(widthPixel, heightPixel));
    }

    public Thumbnail(long id,
                     long sessionId,
                     String name,
                     String uri,
                     FileSize fileSize,
                     ImageSize imageSize) {
        this.id = id;
        this.sessionId = sessionId;
        this.name = name;
        this.uri = uri;
        this.fileSize = fileSize;
        this.imageSize = imageSize;
    }
}
