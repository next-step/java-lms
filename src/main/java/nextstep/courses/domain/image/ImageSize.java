package nextstep.courses.domain.image;

import nextstep.courses.exception.NotValidHeightException;
import nextstep.courses.exception.NotValidRatioException;
import nextstep.courses.exception.NotValidWidthException;

public class ImageSize {

    private static final double MINIMUM_HEIGHT = 300;
    private static final double MINIMUM_WIDTH = 200;
    private static final double FLOATING_POINT_ERROR_RATIO = 0.0001;
    private static final double RATIO = 3.0 / 2.0;

    private final double height;

    private final double width;

    public ImageSize(double height, double width) {
        validateSize(height, width);
        validateRatio(height, width);
        this.height = height;
        this.width = width;
    }

    private void validateRatio(double height, double width) {
        double ratio = height / width;
        if (Math.abs(ratio - RATIO) > FLOATING_POINT_ERROR_RATIO) {
            throw new NotValidRatioException(ratio);
        }
    }

    private void validateSize(double height, double width) {
        validateHeight(height);
        validateWidth(width);
    }

    private void validateWidth(double width) {
        if (width < MINIMUM_WIDTH) {
            throw new NotValidWidthException(width);
        }
    }

    private void validateHeight(double height) {
        if (height < MINIMUM_HEIGHT) {
            throw new NotValidHeightException(height);
        }
    }
}
