package nextstep.session.image;

import java.util.Objects;

public class ImageCapacity {

    private static final int MAXIMUM_IMAGE_CAPACITY = 1;
    private static final String OVER_CAPACITY_MESSAGE = "이미지 사이즈는 1MB를 초과하면 안됩니다.";

    private final int capacity;

    public ImageCapacity(int capacity) {
        confirmImageCapacity(capacity);
        this.capacity = capacity;
    }

    private void confirmImageCapacity(int size) {
        if (size > MAXIMUM_IMAGE_CAPACITY) {
            throw new IllegalArgumentException(OVER_CAPACITY_MESSAGE);
        }
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        ImageCapacity that = (ImageCapacity) object;
        return capacity == that.capacity;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(capacity);
    }
}
