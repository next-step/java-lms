package nextstep.courses.domain.cover;

public class Image {

    private final int size;

    private final ImageType type;

    private final int width;

    private final int height;

    public Image(int size, ImageType type, int width, int height) {
        this.size = size;
        this.type = type;
        this.width = width;
        this.height = height;
    }
}
