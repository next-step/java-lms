package nextstep.courses.domain.images;

public class Image {

    private Long id;

    private ImageType type;

    // KB
    private ImageSizeKb size;

    private Long width;

    private Long height;

    public Image() {
    }

    public Image(Long id, ImageType type, Double size, Long width, Long height) {
        this.id = id;
        this.type = type;
        this.size = new ImageSizeKb(size);
        this.width = width;
        this.height = height;
    }
}
