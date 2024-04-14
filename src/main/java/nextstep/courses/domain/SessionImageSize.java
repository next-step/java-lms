package nextstep.courses.domain;

public class SessionImageSize {
    private final int height;
    private final int width;
    private final int size;

    private static final int MIN_HEIGHT = 200;
    private static final int MIN_WIDTH = 300;
    private static final int MAX_SIZE = 1;
    private static final int WIDTH_RATIO = 3;
    private static final int HEIGHT_RATIO = 2;

    public SessionImageSize(int width, int height, int size) {
        assertValidWidthAndHeight(width, height);
        assertValidSize(size);

        this.height = height;
        this.width = width;
        this.size = size;
    }

    private void assertValidWidthAndHeight(int width, int height) {
        String invalidPixelMessage = "이미지 최소 사이즈는 300 x 200 입니다.";
        String invalidRatioMessage = "이미지 비율은 3:2 여야 합니다.";

        if (height < MIN_HEIGHT && width < MIN_WIDTH) {
            throw new IllegalArgumentException(invalidPixelMessage);
        }

        if (width * HEIGHT_RATIO != height * WIDTH_RATIO) {
            throw new IllegalArgumentException(invalidRatioMessage);
        }
    }

    private void assertValidSize(int size) {
        String invalidSizeMessage = "이미지 최대 크기는 1MB 입니다.";

        if (size < 0 || size > MAX_SIZE) {
            throw new IllegalArgumentException(invalidSizeMessage);
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getSize() {
        return size;
    }
}
