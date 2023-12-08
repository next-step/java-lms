package nextstep.image.domain;

import java.util.Objects;
import nextstep.image.exception.HeightValidationException;
import nextstep.image.exception.RatioValidationException;
import nextstep.image.exception.WidthValidationException;

public class ImageSize {

    public static final String WIDTH_VALIDATION_EXCEPTION = "이미지의 너비는 300픽셀 이상이어야 합니다.";
    public static final String HEIGHT_VALIATION_EXCEPTION = "이미지의 높이는 300픽셀 이상이어야 합니다.";
    public static final String RATIO_VALIDATION_EXCEPTION = "너비 대 높이 비는 3 : 2 이어야 합니다.";

    private double width;
    private double height;

    public ImageSize() {

    }

    public ImageSize(double width, double height) {
        validateWidth(width);
        validateHeight(height);
        validateRatio(width, height);
        this.width = width;
        this.height = height;
    }

    private void validateWidth(double width) {
        if (width < 300) {
            throw new WidthValidationException(WIDTH_VALIDATION_EXCEPTION);
        }
    }

    private void validateHeight(double height) {
        if (height < 200) {
            throw new HeightValidationException(HEIGHT_VALIATION_EXCEPTION);
        }
    }

    private void validateRatio(double width, double height) {
        if (width / height != (double) 3 / 2) {
            throw new RatioValidationException(RATIO_VALIDATION_EXCEPTION);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ImageSize imageSize = (ImageSize) o;
        return width == imageSize.width && height == imageSize.height;
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, height);
    }
}
