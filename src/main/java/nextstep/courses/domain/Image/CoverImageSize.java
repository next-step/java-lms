package nextstep.courses.domain.Image;

import java.util.Objects;

public class CoverImageSize {

    private static final int MAX_IMAGE_SIZE = 1024 * 1024;

    private int size;

    public CoverImageSize(int size) {
        validate(size);
        this.size = size;
    }

    private void validate(int size) {
        if (size > MAX_IMAGE_SIZE) {
            throw new IllegalArgumentException("이미지 사이즈는 1MB를 넘을 수 없습니다");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoverImageSize that = (CoverImageSize) o;
        return size == that.size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(size);
    }
}
