package nextstep.courses.domain;

public class Image {
    private final ImageSize imageSize;
    private final ImageType imageType;

    public Image(int size, String type, int width, int height) {
        this(new ImageSize(size, width, height), ImageType.of(type));
    }

    public ImageSize getImageSize() {
        return imageSize;
    }

    public ImageType getImageType() {
        return imageType;
    }

    public Image(ImageSize imageSize, ImageType imageType) {
        this.imageSize = imageSize;
        this.imageType = imageType;
    }

}
