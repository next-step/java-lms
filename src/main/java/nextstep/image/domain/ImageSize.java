package nextstep.image.domain;

import nextstep.image.exception.HeightValidationException;
import nextstep.image.exception.RatioValidationException;
import nextstep.image.exception.WidthValidationException;

public class ImageSize {

    public static final String WIDTH_VALIDATION_EXCEPTION = "이미지의 너비는 300픽셀 이상이어야 합니다.";
    public static final String HEIGHT_VALIATION_EXCEPTION = "이미지의 높이는 300픽셀 이상이어야 합니다.";
    public static final String RATIO_VALIDATION_EXCEPTION = "너비 대 높이 비는 3 : 2 이어야 합니다.";

    private long width;
    private long height;

    public ImageSize() {

    }

    public ImageSize(long width, long height) {
        validateWidth(width);
        validateHeight(height);
        validateRatio(width, height);
        this.width = width;
        this.height = height;
    }

    private void validateWidth(long width) {
        if (width < 300) {
            throw new WidthValidationException(WIDTH_VALIDATION_EXCEPTION);
        }
    }

    private void validateHeight(long height) {
        if (height < 200) {
            throw new HeightValidationException(HEIGHT_VALIATION_EXCEPTION);
        }
    }

    private void validateRatio(long width, long height) {
        if (width / height != 3 / 2) {
            throw new RatioValidationException(RATIO_VALIDATION_EXCEPTION);
        }
    }
}
