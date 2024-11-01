package nextstep.courses.domain.cover;

public class ImageSize {

    private static final int MAX_IMAGE_SIZE = 1 * 1024 * 1024;

    private final int imageSize;

    private ImageSize(int imageSize) {
        validateImageSize(imageSize);

        this.imageSize = imageSize;
    }

    public static ImageSize of(int imageSize) {
        return new ImageSize(imageSize);
    }

    private void validateImageSize(int imageSize) {
        if (isInvalidImageSize(imageSize)) {
            throw new IllegalArgumentException("이미지 크기는 1MB 이하여야 합니다.");
        }
    }

    private boolean isInvalidImageSize(int imageSize) {
        return imageSize > MAX_IMAGE_SIZE;
    }

    public int getImageSize() {
        return imageSize;
    }
}
