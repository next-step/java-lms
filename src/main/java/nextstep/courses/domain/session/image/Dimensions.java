package nextstep.courses.domain.session.image;

import java.util.Objects;

public class Dimensions {

    private static final int WIDTH_RATIO = 3;
    private static final int HEIGHT_RATIO = 2;

    private final Width width;
    private final Height height;

    public Dimensions(final Width width, final Height height) {
        validateWidthAndHeightHasValidRatio(width, height);

        this.width = width;
        this.height = height;
    }

    private void validateWidthAndHeightHasValidRatio(final Width width, final Height height) {
        if (width.pxByRatio(HEIGHT_RATIO) != height.pxByRatio(WIDTH_RATIO)) {
            throw new IllegalArgumentException("커버 이미지 너비와 높이의 비율이 올바르지 않습니다.");
        }
    }

    @Override
    public boolean equals(final Object otherDimensions) {
        if (this == otherDimensions) {
            return true;
        }

        if (otherDimensions == null || getClass() != otherDimensions.getClass()) {
            return false;
        }

        final Dimensions that = (Dimensions)otherDimensions;

        return Objects.equals(this.width, that.width) &&
                Objects.equals(this.height, that.height);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.width, this.height);
    }
}
