package nextstep.courses.domain.session.image;

import java.util.Objects;

public class Height {

    static final int MINIMUM_HEIGHT = 200;

    private final int px;

    public Height(final int px) {
        validateHeightIsNotLessThanMinimum(px);

        this.px = px;
    }

    private void validateHeightIsNotLessThanMinimum(final int px) {
        if (px < MINIMUM_HEIGHT) {
            throw new IllegalArgumentException("커버 이미지의 높이는 최소 높이 이상이어야 합니다. 너비: " + px);
        }
    }

    public int pxByRatio(final int ratio) {
        return this.px * ratio;
    }

    @Override
    public boolean equals(final Object otherHeight) {
        if (this == otherHeight) {
            return true;
        }

        if (otherHeight == null || getClass() != otherHeight.getClass()) {
            return false;
        }

        return this.px == ((Height)otherHeight).px;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.px);
    }
}
