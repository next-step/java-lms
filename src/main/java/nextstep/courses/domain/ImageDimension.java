package nextstep.courses.domain;

public class ImageDimension {

    public static final int HEIGHT_RATIO = 2;
    public static final int WIDTH_RATIO = 3;
    public static final int MIN_WIDTH = 300;
    public static final int MIN_HEIGHT = 200;
    private int width;
    private int height;

    public ImageDimension(int width, int height) {
        validateMinimumSize(width, height);
        validateRatio(width, height);
        this.width = width;
        this.height = height;
    }

    private static void validateMinimumSize(int width, int height) {
        if (width < MIN_WIDTH || height < MIN_HEIGHT) {
            throw new IllegalArgumentException();
        }
    }

    private static void validateRatio(int width, int height) {
        if (!(HEIGHT_RATIO * width == WIDTH_RATIO * height)) {
            throw new IllegalArgumentException();
        }
    }
}
