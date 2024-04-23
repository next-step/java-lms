package nextstep.courses.domain;

public class ImageSize {

    private final static String INVALID_WIDTH = "이미지의 가로 길이는 300px 이상부터 가능합니다.";
    private final static String INVALID_HEIGHT = "이미지의 세로 길이는 200px 이상부터 가능합니다.";
    private final static String INVALID_RATIO = "이미지의 가로, 세로 비율은 3:2가 되어야 합니다.";
    private final static int MAX_WIDTH = 300;
    private final static int MAX_HEIGHT = 200;
    private final static int WIDTH_RATIO = 3;
    private final static int HEIGHT_RATIO = 2;

    private int width;
    private int height;

    public ImageSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public static ImageSize of(int width, int height) {
        assertConditions(width, height);
        return new ImageSize(width, height);
    }

    private static void assertConditions(int width, int height) {
        if (isValidateWidth(width)) {
            throw new IllegalArgumentException(INVALID_WIDTH);
        }

        if (isValidateHeight(height)) {
            throw new IllegalArgumentException(INVALID_HEIGHT);
        }

        if (!isValidateAspectRatio(width, height)) {
            throw new IllegalArgumentException(INVALID_RATIO);
        }
    }

    private static boolean isValidateAspectRatio(int width, int height) {
        return width * HEIGHT_RATIO == height * WIDTH_RATIO;
    }

    private static boolean isValidateHeight(int height) {
        return MAX_HEIGHT > height;
    }

    private static boolean isValidateWidth(int width) {
        return MAX_WIDTH > width;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
