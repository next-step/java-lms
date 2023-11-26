package nextstep.courses.domain;

public class Thumbnail {

    private final String name;
    private final String uri;
    private final FileSize fileSize;
    private final ImageSize imageSize;

    public Thumbnail(String name,
                     String uri,
                     FileSize fileSize,
                     ImageSize imageSize) {
        this.name = name;
        this.uri = uri;
        this.fileSize = fileSize;
        this.imageSize = imageSize;
    }
}
