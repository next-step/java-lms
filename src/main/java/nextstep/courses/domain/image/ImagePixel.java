package nextstep.courses.domain.image;

public class ImageSize {

    private static final int MIN_WIDTH = 300;
    private static final int MIN_HEIGHT = 200;
    private static final double RATIO = 3 / 2;

    private final int width;
    private final int height;

    public ImageSize(int width, int height) {
        validate(width, height);
        this.width = width;
        this.height = height;
    }

    private void validate(int width, int height) {
        if (width < MIN_WIDTH || height < MIN_HEIGHT) {
            throw new IllegalArgumentException("Invalid image size.");
        }
        if (width / height != RATIO) {
            throw new IllegalArgumentException("Invalid image ratio.");
        }
    }

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }
}
