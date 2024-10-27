package nextstep.session.image;

import java.util.Objects;

public class ImageSize {

    private static final int MAXIMUM_IMAGE_SIZE = 1;
    private static final String OVER_SIZE_MESSAGE = "이미지 사이즈는 1MB를 초과하면 안됩니다.";

    private final int size;

    public ImageSize(int size) {
        confirmImageSize(size);
        this.size = size;
    }

    private void confirmImageSize(int size) {
        if (size > MAXIMUM_IMAGE_SIZE) {
            throw new IllegalArgumentException(OVER_SIZE_MESSAGE);
        }
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        ImageSize imageSize = (ImageSize) object;
        return size == imageSize.size;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(size);
    }
}
