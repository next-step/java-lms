package nextstep.courses.domain.session;

import java.util.Objects;

public class Size {
    private int width;
    private int height;

    public Size(int width, int height) {
        if (!isValidSize(width, height)) {
            throw new IllegalArgumentException("적절한 이미지 사이즈가 아닙니다.");
        }
        this.width = width;
        this.height = height;
    }

    private boolean isValidSize(int width, int height) {
        return width >= 300 && height >= 200 && (double) width / height == 1.5;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Size size = (Size) o;
        return width == size.width && height == size.height;
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, height);
    }
}
