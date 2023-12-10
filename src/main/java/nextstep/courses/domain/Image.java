package nextstep.courses.domain;

public class Image {

    private ImageSize size;

    private ImageType type;


    public Image() {
    }

    public Image(ImageSize size, ImageType type) {
        this.size = size;
        this.type = type;
    }

    public ImageType getType() {
        return this.type;
    }
}
