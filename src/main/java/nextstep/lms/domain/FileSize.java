package nextstep.lms.domain;

public class FileSize {
    private static final int MIN_WIDTH_PX_SIZE = 300;
    private static final int MIN_HEIGHT_PX_SIZE = 200;
    private static final double WIDTH_HEIGHT_RATIO = MIN_WIDTH_PX_SIZE / (double) MIN_HEIGHT_PX_SIZE;

    private final int width;
    private final int height;

    public FileSize(int width, int height) {
        this.width = widthChecking(width);
        this.height = heightChecking(height);
        ratioCheck(width, height);
    }

    private int widthChecking(int value) {
        if (value >= MIN_WIDTH_PX_SIZE) {
            return value;
        }
        throw new IllegalArgumentException("이미지 너비가 너무 작습니다.");
    }

    private int heightChecking(int value) {
        if (value >= MIN_HEIGHT_PX_SIZE) {
            return value;
        }
        throw new IllegalArgumentException("이미지 높이가 너무 작습니다.");
    }

    private void ratioCheck(int width, int height) {
        if ((width / (double) height) != WIDTH_HEIGHT_RATIO) {
            throw new IllegalArgumentException("이미지의 비율이 맞지 않습니다.");
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
