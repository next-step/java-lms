package nextstep.courses.domain.session.coverImage;

public class ImageDimensions {
    private int width;
    private int height;

    private static final int MIN_WIDTH = 300;
    private static final int MIN_HEIGHT = 200;
    private static final int WIDTH_RATIO = 3;
    private static final int HEIGHT_RATIO = 2;

    public ImageDimensions(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public boolean validDimensions() {
        if (width < MIN_WIDTH || height < MIN_HEIGHT) {
            throw new IllegalArgumentException("이미지의 너비는 300픽셀, 높이는 200픽셀 이상이어야 합니다.");
        }
        if (width * HEIGHT_RATIO != height * WIDTH_RATIO) {
            throw new IllegalArgumentException("너비와 높이의 비율은 3:2여야 합니다.");
        }
        return true;
    }
}
