package nextstep.courses.domain.image;

public class Image {

    private final Long id;
    private final ImageName name;
    private final ImageSize size;
    private final ImagePixel pixel;

    public Image(Long id, ImageName name, ImageSize size, ImagePixel pixel) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.pixel = pixel;
    }
}
