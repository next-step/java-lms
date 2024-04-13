package nextstep.courses.domain.session;

import static nextstep.courses.ExceptionMessage.INVALID_COVER_IMAGE;

public class Dimensions {
    private static final double MIN_WIDTH = 300;
    private static final double MIN_HEIGHT = 200;
    private static final double RATIO_OF_WIDTH_HEIGHT = 3.0 / 2.0;

    private final double width;
    private final double height;

    public Dimensions(double width, double height) {
        validateCoverImageWidthHeightInput(width, height);
        this.width = width;
        this.height = height;
    }

    private void validateCoverImageWidthHeightInput(double width, double height) {
        if (!isValidWidthHeight(width, height)) {
            throw new IllegalArgumentException(INVALID_COVER_IMAGE.message());
        }
    }

    private boolean isValidWidthHeight(double width, double height) {
        return width >= MIN_WIDTH && height >= MIN_HEIGHT && (width / height == RATIO_OF_WIDTH_HEIGHT);
    }
}
