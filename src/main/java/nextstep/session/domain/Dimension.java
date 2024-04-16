package nextstep.session.domain;

import nextstep.session.InvalidImageConditionsException;

public class Dimension {

    private final int MIN_WIDTH = 300;
    private final int MIN_HEIGHT = 200;
    private final int WIDTH_RATIO = 3;
    private final int HEIGHT_RATIO = 2;
    private final long width;
    private final long height;

    public Dimension(long width, long height) throws InvalidImageConditionsException {
        validate(width, height);
        this.width = width;
        this.height = height;
    }

    private void validate(long width, long height) throws InvalidImageConditionsException {
        if (!validateDimensions(width, height)) {
            throw new InvalidImageConditionsException(
                String.format("이미지는 최소 %d*%d 이상이여야합니다. 입력된 사이즈 %d*%d", MIN_WIDTH, MIN_HEIGHT, width,
                    height));
        }
        if (!validateRatio(width, height)) {
            throw new InvalidImageConditionsException(
                String.format("너비와 높이가 %d:%d 비율이여야 합니다.", WIDTH_RATIO, HEIGHT_RATIO));
        }
    }

    private boolean validateDimensions(long width, long height) {
        if (width < MIN_WIDTH || height < MIN_HEIGHT) {
            return false;
        }
        return true;
    }

    private boolean validateRatio(long width, long height) {
        if ((width * HEIGHT_RATIO) != (height * WIDTH_RATIO)) {
            return false;
        }
        return true;
    }
}
