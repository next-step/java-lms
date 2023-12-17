package nextstep.courses.domain.session.image;

public class ImageSize {
    private final int MAXIMUM_WIDTH = 300;
    private final int MAXIMUM_HEIGHT = 200;
    private int width;
    private int height;

    public ImageSize(int width, int height) {
        this.width = width;
        this.height = height;
        checkValidWidthAndHeight();
        checkValidRatio();
    }

    private void checkValidWidthAndHeight() {
        if (width < MAXIMUM_WIDTH) {
            throw new IllegalArgumentException("이미지의 너비는 최소 300 이상이어야 합니다.");
        }
        if (height < MAXIMUM_HEIGHT) {
            throw new IllegalArgumentException("이미지의 높이는 최소 200 이상이어야 합니다.");
        }
    }

    private void checkValidRatio() {
        if (width * 2 != height * 3) {
            throw new IllegalArgumentException("이미지의 너비와 높이의 비율은 3:2이어야 합니다.");
        }
    }
}
