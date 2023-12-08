package nextstep.image.domain;

public class Image {

    private ImageCapacity imageCapacity;

    private ImageType imageType;

    private ImageSize imageSize;

    public Image(ImageCapacity imageCapacity, ImageType imageType, ImageSize imageSize) {
        this.imageCapacity = imageCapacity;
        this.imageType = imageType;
        this.imageSize = imageSize;
    }
}
