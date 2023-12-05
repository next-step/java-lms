package nextstep.courses.domain.field;

public class CoverImage {
    private final long MIN_WIDTH = 300;
    private final long MIN_HEIGHT = 200;

    private double size;

    private long width;

    private long height;

    private ImageType imageType;

    public CoverImage(double size, long width, long height, ImageType imageType) {
        verifySize(size);
        verifyWidthHeight(width, height);

        this.size = size;
        this.width = width;
        this.height = height;
        this.imageType = imageType;
    }

    private void verifyWidthHeight(long width, long height) {
        if (width < this.MIN_WIDTH || height < this.MIN_HEIGHT) {
            throw new IllegalArgumentException("이미지의 width는 300픽셀, height는 200픽셀 이상만 가능합니다.");
        }
    }

    private void verifySize(double size) {
        if (size > 1) {
            throw new IllegalArgumentException("이미지 크기는 1MB 이하만 가능합니다.");
        }
    }
}
