package nextstep.courses.domain.image;

public class ImageDimension {

    private static final int MIN_WIDTH_PIXEL = 300;
    private static final int MIN_HEIGHT_PIXEL = 200;
    private static final int WIDTH_RATIO = 3;
    private static final int HEIGHT_RATIO = 2;

    private final int width;
    private final int height;

    public ImageDimension(int width, int height) {
        validate(width, height);
        this.width = width;
        this.height = height;
    }

    private void validate(int width, int height) {
        validateDimension(width, height);
        validateAspectRatio(width, height);
    }

    private void validateDimension(int width, int height) {
        if (width < MIN_WIDTH_PIXEL) {
            throw new RuntimeException(String.format("이미지 너비는 %d픽셀 이상이어야 합니다.", MIN_WIDTH_PIXEL));
        }
        if (height < MIN_HEIGHT_PIXEL) {
            throw new RuntimeException(String.format("이미지 높이는 %d픽셀 이상이어야 합니다.", MIN_HEIGHT_PIXEL));
        }
    }

    private void validateAspectRatio(int width, int height) {
        if (width * HEIGHT_RATIO != height * WIDTH_RATIO) {
            throw new RuntimeException(String.format("이미지 비율은 %d:%d여야 합니다.", WIDTH_RATIO, HEIGHT_RATIO));
        }
    }
}
