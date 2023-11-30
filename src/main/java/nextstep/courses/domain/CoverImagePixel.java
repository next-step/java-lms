package nextstep.courses.domain;

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
            throw new IllegalArgumentException(String.format("가로 픽셀은 최소 %d픽셀 이상이어야 합니다. 현재 픽셀 : %d", MIN_WIDTH_SIZE, width));
        }

        if (height < MIN_HEIGHT_SIZE) {
            throw new IllegalArgumentException(String.format("세로 픽셀은 최소 %d픽셀 이상이어야 합니다. 현재 픽셀 : %d", MIN_HEIGHT_SIZE, height));
        }

        if (isInvalidRatio(width, height)) {
            throw new IllegalArgumentException(String.format("가로와 세로의 비율은 %.1f 여야 합니다. 현재비율 : %.1f", WIDTH_HEIGHT_RATIO, (double) width / height));
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
