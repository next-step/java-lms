package nextstep.courses.domain.image;

public class SessionCoverImage {

    private final ImageFileSize fileSize;
    private final ImageSize size;
    private final ImageExtension extension;

    public SessionCoverImage(long size, int width, int height, String extension) {
        this(new ImageFileSize(size), new ImageSize(width, height), ImageExtension.get(extension));
    }

    private SessionCoverImage(ImageFileSize fileSize, ImageSize size, ImageExtension extension) {
        this.fileSize = fileSize;
        this.size = size;
        this.extension = extension;
    }

}
