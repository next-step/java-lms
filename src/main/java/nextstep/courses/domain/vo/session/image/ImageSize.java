package nextstep.courses.domain.vo.session.image;

import nextstep.courses.ImageSizeExceededException;

import java.util.Objects;

public class ImageSize {
    public static final int MAX_IMAGE_SIZE = 1024 * 1024;
    public static final String IMAGE_SIZE_EXCEED_MESSAGE = "이미지 크기는 1MB 이하여야 한다";
    private final int size;

    public ImageSize(int size) {
        if (size > MAX_IMAGE_SIZE) {
            throw new ImageSizeExceededException(IMAGE_SIZE_EXCEED_MESSAGE);
        }
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImageSize imageSize = (ImageSize) o;
        return size == imageSize.size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(size);
    }
}
