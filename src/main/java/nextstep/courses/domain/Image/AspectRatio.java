package nextstep.courses.domain.Image;

import nextstep.courses.exception.ImageException;

import java.util.Objects;

public class AspectRatio {

    private static final int HEIGHT_RATIO = 3;
    private static final int WIDTH_RATIO = 2;
    private final int width;
    private final int height;

    public AspectRatio(int width, int height) {
        validatePixel(width, height);
        validateRatio(width, height);

        this.width = width;
        this.height = height;
    }

    private static void validatePixel(int width, int height) {
        if (width < 300 || height < 200) {
            throw new ImageException("이미지의 width는 300픽셀, height는 200픽셀 이상이어야 합니다");
        }
    }

    private void validateRatio(int width, int height) {
        if (isBadRatio(width, height)) {
            throw new ImageException(" width와 height의 비율은 3:2 이어야 합니다.");
        }
    }

    private boolean isBadRatio(int width, int height) {
        return width * WIDTH_RATIO != height * HEIGHT_RATIO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AspectRatio that = (AspectRatio) o;
        return width == that.width && height == that.height;
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, height);
    }
}
