package nextstep.courses.domain;

import nextstep.courses.InvalidValueException;

import java.util.Objects;

public class CoverImagePixel {

    private static final int MIN_WIDTH_SIZE = 300;
    private static final int MIN_HEIGHT_SIZE = 200;
    private static final double WIDTH_HEIGHT_RATIO = 1.5d;

    private final int width;
    private final int height;

    public CoverImagePixel(int width, int height) {
        validate(width, height);

        this.width = width;
        this.height = height;
    }

    private void validate(int width, int height) {
        if (width < MIN_WIDTH_SIZE) {
            throw new InvalidValueException("가로 픽셀은 최소 300픽셀 이상이어야 합니다.");
        }

        if (height < MIN_HEIGHT_SIZE) {
            throw new InvalidValueException("세로 픽셀은 최소 200픽셀 이상이어야 합니다.");
        }

        if (isInvalidRatio(width, height)) {
            throw new InvalidValueException("가로와 세로의 비율은 3:2 여야 합니다.");
        }
    }

    private boolean isInvalidRatio(int width, int height) {
        return (double) width / height != WIDTH_HEIGHT_RATIO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoverImagePixel that = (CoverImagePixel) o;
        return width == that.width && height == that.height;
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, height);
    }
}
