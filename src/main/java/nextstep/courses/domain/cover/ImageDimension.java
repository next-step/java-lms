package nextstep.courses.domain.cover;

public class ImageDimension {

    private static final int MIN_WIDTH = 300;
    private static final int MIN_HEIGHT = 200;
    private static final int IMAGE_RATIO_WIDTH_MULTIPLIER = 2;
    private static final int IMAGE_RATIO_HEIGHT_MULTIPLIER = 3;

    private final int width;
    private final int height;

    private ImageDimension(int width, int height) {
        validateDimension(width, height);
        validateRatio(width, height);

        this.width = width;
        this.height = height;
    }

    private void validateDimension(int width, int height) {
        if (isInvalidImageSize(width, height)) {
            throw new IllegalArgumentException("이미지의 크기가 유효하지 않습니다.");
        }
    }

    private boolean isInvalidImageSize(int width, int height) {
        return width < MIN_WIDTH || height < MIN_HEIGHT;
    }

    private void validateRatio(int width, int height) {
        if (isInvalidImageRatio(width, height)) {
            throw new IllegalArgumentException("이미지의 비율이 유효하지 않습니다.");
        }
    }

    private boolean isInvalidImageRatio(int width, int height) {
        return IMAGE_RATIO_WIDTH_MULTIPLIER * width != IMAGE_RATIO_HEIGHT_MULTIPLIER * height;
    }

    public static ImageDimension of(int width, int height) {
        return new ImageDimension(width, height);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
