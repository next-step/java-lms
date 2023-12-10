package nextstep.Session;

public class ImageSize {

    private static final long MAX_IMAGE_SIZE = 1_000_000;

    private long imageSize;

    private ImageSize(long imageSize) {
        validateImageSize(imageSize);
        this.imageSize = imageSize;
    }

    private void validateImageSize(final long imageSize) {
        if (imageSize > MAX_IMAGE_SIZE) {
            throw new IllegalArgumentException("이미지 크기는 1MB를 초과할 수 없습니다.");
        }
    }

    public static ImageSize from(final long imageSize) {
        return new ImageSize(imageSize);
    }
}
