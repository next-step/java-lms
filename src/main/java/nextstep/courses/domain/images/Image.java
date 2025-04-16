package nextstep.courses.domain.images;

public class Image {

    private ImageType type;

    // KB
    private ImageSizeKb size;

    private ImageDimension dimension;

    public Image() {
    }

    public Image(ImageType type, double size, double width, double height) {
        this.type = type;
        this.size = new ImageSizeKb(size);
        this.dimension = new ImageDimension(width, height);
    }
}
