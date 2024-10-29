package nextstep.courses.domain.session.image;

import nextstep.courses.ImageSizeExceededException;

import java.util.Objects;

public class ImageFileSize {
    public static final int MAX_IMAGE_SIZE_MB = 1024 * 1024;
    public static final String IMAGE_SIZE_EXCEED_MESSAGE = "이미지 크기는 1MB 이하여야 한다";
    private final int size;

    public ImageFileSize(int size) {
        if (size > MAX_IMAGE_SIZE_MB) {
            throw new ImageSizeExceededException(IMAGE_SIZE_EXCEED_MESSAGE);
        }
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImageFileSize imageFileSize = (ImageFileSize) o;
        return size == imageFileSize.size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(size);
    }
}
