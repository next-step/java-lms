package nextstep.image.domain;

import nextstep.image.exception.WidthValidationException;

public class ImageSize {

    public static final String WIDTH_VALIDATION_EXCEPTION = "이미지의 너비는 300픽셀 이상이어야 합니다.";

    private long width;
    private long height;

    public ImageSize(int width) {
        validateWidth(width);
        this.width = width;
    }

    private void validateWidth(int width) {
        if (width < 300) {
            throw new WidthValidationException(WIDTH_VALIDATION_EXCEPTION);
        }
    }
}
