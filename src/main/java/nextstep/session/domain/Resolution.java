package nextstep.session.domain;

public class Resolution {

    public static final int MINIMUM_WIDTH = 300;
    public static final int MINIMUM_HEIGHT = 200;
    public static final int HEIGHT_RATE = 2;
    public static final int WIDTH_RATE = 3;
    private final int width;
    private final int height;

    public Resolution(int width, int height) {
        validate(width, height);

        this.width = width;
        this.height = height;
    }

    private void validate(int width, int height) {
        if (width < MINIMUM_WIDTH) {
            throw new IllegalArgumentException(String.format("가로 길이는 %d이상이여야 합니다. 현재 값=%d", MINIMUM_WIDTH, width));
        }

        if (height < MINIMUM_HEIGHT) {
            throw new IllegalArgumentException(String.format("세로 길이는 %d이상이여야 합니다. 현재 값=%d", MINIMUM_HEIGHT, height));
        }

        if (WIDTH_RATE * height != HEIGHT_RATE * width) {
            throw new IllegalArgumentException("가로 길이와 세로 길이의 비율은 3:2이여야 합니다.");
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
