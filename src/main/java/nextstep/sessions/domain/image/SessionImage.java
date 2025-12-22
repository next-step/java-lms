package nextstep.sessions.domain.image;

public class SessionImage {

    private final FileName fileName;
    private final ImageSize imageSize;
    private final ImageDimension imageDimension;
    private final ImageType type;

    SessionImage(String fileName, long size, int width, int height) {
        this(new FileName(fileName), new ImageSize(size), new ImageDimension(width, height));
    }

    public SessionImage(FileName fileName, ImageSize imageSize, ImageDimension imageDimension) {
        this.fileName = fileName;
        this.imageSize = imageSize;
        this.imageDimension = imageDimension;
        this.type = ImageType.from(fileName.extension());
    }

    public String fileName() {
        return fileName.value();
    }

    public long size() {
        return imageSize.value();
    }

}
