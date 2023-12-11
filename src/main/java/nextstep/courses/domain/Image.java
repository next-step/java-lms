package nextstep.courses.domain;

public class Image {

    private final ImageSize size;

    private final ImageType type;

    public Image(ImageSize size, ImageType type) {
        this.size = size;
        this.type = type;
    }

    public ImageType getType() {
        return this.type;
    }

    public ImageSize getSize() {
        return size;
    }
}
