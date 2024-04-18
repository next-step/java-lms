package nextstep.courses.domain.session.image;

import java.util.Objects;

public class Width {

    static final int MINIMUM_WIDTH = 300;

    private final int px;

    public Width(final int px) {
        validateWidthIsNotLessThanMinimum(px);

        this.px = px;
    }

    private void validateWidthIsNotLessThanMinimum(final int px) {
        if (px < MINIMUM_WIDTH) {
            throw new IllegalArgumentException("커버 이미지의 너비는 최소 너비 이상이어야 합니다. 너비: " + px);
        }
    }

    public int pxByRatio(final int ratio) {
        return this.px * ratio;
    }

    public int px() {
        return this.px;
    }

    @Override
    public boolean equals(final Object otherWidth) {
        if (this == otherWidth) {
            return true;
        }

        if (otherWidth == null || getClass() != otherWidth.getClass()) {
            return false;
        }

        return this.px == ((Width)otherWidth).px;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.px);
    }
}
