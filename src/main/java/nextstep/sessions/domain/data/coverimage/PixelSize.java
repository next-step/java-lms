package nextstep.sessions.domain.data.coverimage;

import nextstep.sessions.domain.exception.CannotSaveCoverImageException;

public class PixelSize {

    private static final int IMAGE_WIDTH_RATIO = 3;
    private static final int IMAGE_HEIGHT_RATIO = 2;
    private static final int IMAGE_MIN_SIZE = 100;
    private static final int IMAGE_MIN_WIDTH;
    private static final int IMAGE_MIN_HEIGHT;
    public static final String IMAGE_SIZE_VALIDATION_MESSAGE;
    public static final String IMAGE_RATIO_VALIDATION_MESSAGE;

    static {
        IMAGE_MIN_WIDTH = IMAGE_WIDTH_RATIO * IMAGE_MIN_SIZE;
        IMAGE_MIN_HEIGHT = IMAGE_HEIGHT_RATIO * IMAGE_MIN_SIZE;
        IMAGE_SIZE_VALIDATION_MESSAGE = String.format("이미지 크기는 가로 %d, 세로 %d 이상이어야 합니다.", IMAGE_MIN_WIDTH, IMAGE_MIN_HEIGHT);
        IMAGE_RATIO_VALIDATION_MESSAGE = String.format("이미지 비율은 가로 %d, 세로 %d 여야 합니다.", IMAGE_WIDTH_RATIO, IMAGE_HEIGHT_RATIO);
    }

    private final int width;
    private final int height;

    public PixelSize(int width, int height) {
        validateWidthAndHeight(width, height);
        this.width = width;
        this.height = height;
    }

    private void validateWidthAndHeight(int width, int height) {
        if (!isValidWidthAndHeightSize(width, height)) {
            throw new CannotSaveCoverImageException(IMAGE_SIZE_VALIDATION_MESSAGE);
        }
        if (!isValidRatio(width, height)) {
            throw new CannotSaveCoverImageException(IMAGE_RATIO_VALIDATION_MESSAGE);
        }
    }

    private boolean isValidWidthAndHeightSize(int width, int height) {
        return width >= IMAGE_MIN_WIDTH && height >= IMAGE_MIN_HEIGHT;
    }

    private boolean isValidRatio(int width, int height) {
        return width * IMAGE_HEIGHT_RATIO == height * IMAGE_WIDTH_RATIO;
    }

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }
}
