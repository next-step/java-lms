package nextstep.image.domain;

import nextstep.image.exception.HeightValidationException;
import nextstep.image.exception.WidthValidationException;

public class ImageSize {

    public static final String WIDTH_VALIDATION_EXCEPTION = "이미지의 너비는 300픽셀 이상이어야 합니다.";
    public static final String HEIGHT_VALIATION_EXCEPTION = "이미지의 높이는 300픽셀 이상이어야 합니다.";

    private long width;
    private long height;

    public ImageSize() {

    }

    public ImageSize(int width, int height) {
        validateWidth(width);
        validateHeight(height);
        this.width = width;
        this.height = height;
    }

    private void validateWidth(int width) {
        if (width < 300) {
            throw new WidthValidationException(WIDTH_VALIDATION_EXCEPTION);
        }
    }

    private void validateHeight(int height) {
        if (height < 200) {
            throw new HeightValidationException(HEIGHT_VALIATION_EXCEPTION);
        }
    }
}
