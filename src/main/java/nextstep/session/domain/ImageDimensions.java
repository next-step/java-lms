package nextstep.session.domain;

public class ImageDimensions {

    private static final int MIN_WIDTH = 300;
    private static final int MIN_HEIGHT = 200;
    private static final double ASPECT_RATIO = 1.5;

    private final int width;
    private final int height;

    public ImageDimensions(int width, int height) {
        validateDimension(width, height);
        this.width = width;
        this.height = height;
    }

    public void validateDimension(int width, int height) {
        if (width < MIN_WIDTH || height < MIN_HEIGHT) {
            throw new IllegalArgumentException("너비는 " + MIN_WIDTH + "픽셀, 높이는 " + MIN_HEIGHT + "픽셀 이상이어야 합니다.");
        }

        if ((double) width / height != ASPECT_RATIO) {
            throw new IllegalArgumentException("너비와 높이의 비율은 " + ASPECT_RATIO + "이어야 합니다.");
        }
    }

}
