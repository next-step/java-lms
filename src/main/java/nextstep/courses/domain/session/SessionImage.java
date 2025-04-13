package nextstep.courses.domain.session;

import nextstep.courses.InvalidImageException;

import java.util.Objects;

public class SessionImage {
    private static final int MAX_IMAGE_SIZE = 1000000;
    private static final int MIN_WIDTH = 300;
    private static final int MIN_HEIGHT = 200;
    private static final double WIDTH_PER_HEIGHT = 1.5;

    private final int width;
    private final int height;
    private final int size;
    private final SessionImageType imageType;

    public SessionImage(int width, int height, int size, String imageType) {
        this(width, height, size, SessionImageType.of(imageType));
    }

    public SessionImage(int width, int height, int size, SessionImageType imageType) {
        this.width = width;
        this.height = height;
        this.size = size;
        this.imageType = imageType;
        checkValidSessionImage();
    }

    private void checkValidSessionImage() {
        if (this.width < MIN_WIDTH || this.height < MIN_HEIGHT) {
            throw new InvalidImageException("이미지의 width는 300픽셀, height는 200픽셀 이상이어야 한다.");
        }
        if ((double) this.width / this.height != WIDTH_PER_HEIGHT) {
            throw new InvalidImageException("이미지의 width, height의 비율은 3:2여야 한다.");
        }

        if (this.size > MAX_IMAGE_SIZE) {
            throw new InvalidImageException("이미지의 크기는 1MB 이하여야 한다.");
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, height, size, imageType);
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        SessionImage image = (SessionImage) object;
        return Objects.equals(image.imageType, imageType) && image.width == width && image.height == height && image.size == size;
    }
}
