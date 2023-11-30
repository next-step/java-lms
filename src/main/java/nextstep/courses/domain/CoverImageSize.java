package nextstep.courses.domain;

import java.util.Objects;

public class CoverImageSize {

    private static final int MAX_IMAGE_SIZE = 1024 * 1024;
    private final int imageSize;

    public CoverImageSize(int imageSize) {
        validate(imageSize);

        this.imageSize = imageSize;
    }

    public int getImageSize() {
        return imageSize;
    }

    private void validate(int imageSize) {
        if (imageSize <= 0) {
            throw new IllegalArgumentException("이미지 사이즈는 0이거나 0보다작을 수 없습니다.");
        }

        if (imageSize > MAX_IMAGE_SIZE) {
            throw new IllegalArgumentException("이미지 사이즈는 1MB를 넘을 수 없습니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoverImageSize that = (CoverImageSize) o;
        return imageSize == that.imageSize;
    }

    @Override
    public int hashCode() {
        return Objects.hash(imageSize);
    }
}
