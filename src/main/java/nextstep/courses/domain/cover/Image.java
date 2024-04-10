package nextstep.courses.domain.cover;

public class Image {

    private final ImageSize size;

    private final ImageType type;

    private final int width;

    private final int height;

    public Image(ImageSize size, ImageType type, int width, int height) {
        this.size = size;
        this.type = type;
        this.width = width;
        this.height = height;
    }
}
