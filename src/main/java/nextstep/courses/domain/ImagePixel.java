package nextstep.courses.domain;

public class ImagePixel {
    private static final int MIN_WIDTH = 300;
    private static final int MIN_HEIGHT = 200;
    private static final int WIDTH_RATIO = MIN_WIDTH / 100;
    private static final int HEIGHT_RATIO = MIN_HEIGHT / 100;
    private static final String HEIGHT_SIZE_ERROR_MESSAGE = "이미지의 높이는 %d pixel 이상이여야 합니다.";
    private static final String WIDTH_SIZE_ERROR_MESSAGE = "이미지의 너비는 %d pixel 이상이여야 합니다.";
    private static final String IMAGE_RATIO_ERROR_MESSAGE = "이미지의 비율은 %d:%d여야 합니다.";
    private int width;
    private int height;

    public ImagePixel(int width, int height) {
        validateImagePixel(width, height);
        this.width = width;
        this.height = height;
    }

    private void validateImagePixel(int width, int height) {
        validWidth(width);
        validHeight(height);
        validRate(width, height);
    }

    private void validHeight(int height) {
        if (height < MIN_HEIGHT) {
            throw new IllegalArgumentException(String.format(HEIGHT_SIZE_ERROR_MESSAGE, MIN_HEIGHT));
        }
    }

    private void validWidth(int width) {
        if (width < MIN_WIDTH) {
            throw new IllegalArgumentException(String.format(WIDTH_SIZE_ERROR_MESSAGE, MIN_WIDTH));
        }
    }

    private void validRate(int width, int height) {
        if (!isCorrectRatio(width, height)) {
            throw new IllegalArgumentException(String.format(IMAGE_RATIO_ERROR_MESSAGE, WIDTH_RATIO, HEIGHT_RATIO));
        }
    }

    private static boolean isCorrectRatio(int width, int height) {
        return HEIGHT_RATIO * width == WIDTH_RATIO * height;
    }


}
