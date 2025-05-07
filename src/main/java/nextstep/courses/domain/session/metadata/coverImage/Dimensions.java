package nextstep.courses.domain.session.metadata.coverImage;

import java.util.Objects;

public class Dimensions {
    private static final int MIN_WIDTH = 300;
    private static final int MIN_HEIGHT = 200;
    private static final double RATIO = 3.0 / 2.0;

    private final int width;
    private final int height;

    public Dimensions(int width, int height) {
        this.width = width;
        this.height = height;
        validate();
    }

    private void validate() {
        if (width < MIN_WIDTH || height < MIN_HEIGHT) {
            throw new IllegalArgumentException("최소 300×200 이상이어야 합니다.");
        }
        if (Math.abs(((double) width / height) - RATIO) > 0.01) {
            throw new IllegalArgumentException("비율이 3:2여야 합니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;
        Dimensions that = (Dimensions)o;
        return width == that.width && height == that.height;
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, height);
    }

    /* getter */
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
