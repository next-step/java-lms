package nextstep.courses.domain.image;

public class ImagePixel {
    private static final int MIN_WIDTH = 300;
    private static final int MIn_HEIGHT = 200;
    public static final String INVALID_PIXEL_MSG = "이미지의 해상도는 300x200이상 이어야 합니다.";
    public static final String INVALID_RATIO_MSG = "이미지의 너비와 높이 비율이 3:2가 아닙니다.";
    private int width;
    private int height;

    public ImagePixel(final int width, final int height) {
        checkPixel(width, height);
        checkRatio(width, height);

        this.width = width;
        this.height = height;
    }

    private void checkRatio(final int width, final int height) {
        if (width * 2 != height * 3) {
            throw new IllegalArgumentException(INVALID_RATIO_MSG);
        }
    }

    private void checkPixel(final int width, final int height) {
        if (width < MIN_WIDTH || height < MIn_HEIGHT) {
            throw new IllegalArgumentException(INVALID_PIXEL_MSG);
        }
    }

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }
}
