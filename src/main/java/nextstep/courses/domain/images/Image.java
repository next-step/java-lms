package nextstep.courses.domain.images;

public class Image {

    private Long id;

    private ImageType type;

    // KB
    private ImageSizeKb size;

    private ImageDimension dimension;

    public Image() {
    }

    public Image(Long id, ImageType type, Double size, Double width, Double height) {
        this.id = id;
        this.type = type;
        this.size = new ImageSizeKb(size);
        this.dimension = new ImageDimension(width, height);
    }
}
