package nextstep.courses.domain.cover;

public class ImageDimension {

    private static final int MIN_WIDTH = 300;
    private static final int MIN_HEIGHT = 200;
    private static final double ASPECT_RATIO = 3.0 / 2.0;

    private final int width;
    private final int height;

    private ImageDimension(int width, int height) {
        validateDimension(width, height);

        this.width = width;
        this.height = height;
    }

    public static ImageDimension of(int width, int height) {
        return new ImageDimension(width, height);
    }

    private void validateDimension(int width, int height) {
        if (isInvalidImageSize(width, height)) {
            throw new IllegalArgumentException("이미지의 크기와 비율이 유효하지 않습니다.");
        }
    }

    private boolean isInvalidImageSize(int width, int height) {
        return width < MIN_WIDTH || height < MIN_HEIGHT || (double) width / height != ASPECT_RATIO;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
