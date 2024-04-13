package nextstep.courses.domain.session.image;

public class ImageSize {
    public static final int VALID_WIDTH = 300;
    public static final int VALID_HEIGHT = 200;
    public static final int VALID_WIDTH_RATIO = 3;
    public static final int VALID_HEIGHT_RATIO = 2;
    public static final String INVALID_WIDTH = String.format("이미지의 가로 길이는 %d pixel 이상부터 가능합니다.", VALID_WIDTH);
    public static final String INVALID_HEIGHT = String.format("이미지의 세로 길이는 %d pixel 이상부터 가능합니다.", VALID_HEIGHT);
    public static final String INVALID_IMAGE_RATIO = String.format("이미지의 가로, 세로 비율은 %d:%d가 되어야 합니다.", VALID_WIDTH_RATIO, VALID_HEIGHT_RATIO);

    private int width;
    private int height;

    public ImageSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public static ImageSize of(int width, int height) {
        assertAllConditions(width, height);
        return new ImageSize(width, height);
    }

    private static void assertAllConditions(int width, int height) {
        if (!isValidWidth(width)) {
            throw new IllegalArgumentException(INVALID_WIDTH);
        }

        if (!isValidHeight(height)) {
            throw new IllegalArgumentException(INVALID_HEIGHT);
        }

        if (!isValidImageSizeRatio(width, height)) {
            throw new IllegalArgumentException(INVALID_IMAGE_RATIO);
        }
    }

    private static boolean isValidWidth(int width) {
        if (VALID_WIDTH > width) {
            return false;
        }

        return true;
    }

    private static boolean isValidHeight(int height) {
        if (VALID_HEIGHT > height) {
            return false;
        }

        return true;
    }

    private static boolean isValidImageSizeRatio(int width, int height) {
        return width * VALID_HEIGHT_RATIO == height * VALID_WIDTH_RATIO;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
