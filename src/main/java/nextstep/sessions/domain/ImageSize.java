package nextstep.sessions.domain;

public class ImageSize {
    private static final int MIN_WIDTH = 300;
    private static final int MIN_HEIGHT = 200;

    private final int value;

    public static ImageSize width(int value) {
        assertValidWidth(value);
        return new ImageSize(value);
    }

    public static ImageSize height(int value) {
        assertValidHeight(value);
        return new ImageSize(value);
    }

    private static void assertValidWidth(int width) {
        if (width < MIN_WIDTH) {
            throw new IllegalArgumentException("이미지 너비는 " + MIN_WIDTH + "픽셀 이상이어야 합니다.");
        }
    }

    private static void assertValidHeight(int height) {
        if (height < MIN_HEIGHT) {
            throw new IllegalArgumentException("이미지 높이는 " + MIN_HEIGHT + "픽셀 이상이어야 합니다.");
        }
    }

    public ImageSize(int value) {
        this.value = value;
    }

    public double doubleValue() {
        return (double) this.value;
    }

}
