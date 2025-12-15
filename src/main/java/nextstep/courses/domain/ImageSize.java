package nextstep.courses.domain;

import java.util.Objects;

public class ImageSize {

    public static final int MAX_SIZE = 1_048_576;
    private int imageSize;

    public ImageSize(int imageSize) {
        if (imageSize > MAX_SIZE) {
            throw new IllegalArgumentException();
        }
        this.imageSize = imageSize;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        ImageSize imageSize1 = (ImageSize) object;
        return imageSize == imageSize1.imageSize;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(imageSize);
    }
}
