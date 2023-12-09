package nextstep.courses.domain.image;

public class CoverImage {
    private static final String INVALID_IMAGE_SIZE_MESSAGE = "이미지 사이즈는 1MB를 초과 할 수 없습니다.";
    private static final String INVALID_IMAGE_PIXEL_MESSAGE = "이미지의 해상도는 300x200이상 이어야 합니다.";
    private static final String INVALID_IMAGE_RATIO_MESSAGE = "이미지의 너비와 높이 비율이 3:2가 아닙니다.";

    private static final int MIN_WIDTH = 300;
    private static final int MIN_HEIGHT = 200;
    private static final Long MAX_SIZE = 1024L * 1024L;
    private final Long id;

    private final int width;
    private final int height;
    private final long size;
    private final ImageType imageType;


    public CoverImage(int width, int height, long size, ImageType imageType) {
        this(null, width, height, size, imageType);
    }

    public CoverImage(Long id, int width, int height, long size, ImageType imageType) {
        validateImage(size, width, height);
        this.id = id;
        this.width = width;
        this.height = height;
        this.size = size;
        this.imageType = imageType;
    }

    private void validateImage(final long size, final int width, final int height) {
        validateSize(size);
        validatePixel(width, height);
        validateRatio(width, height);
    }

    private void validateSize(final long size) {
        if (size > MAX_SIZE) {
            throw new IllegalArgumentException(INVALID_IMAGE_SIZE_MESSAGE);
        }
    }

    private void validateRatio(final int width, final int height) {
        if (width * 2 != height * 3) {
            throw new IllegalArgumentException(INVALID_IMAGE_RATIO_MESSAGE);
        }
    }

    private void validatePixel(final int width, final int height) {
        if (width < MIN_WIDTH || height < MIN_HEIGHT) {
            throw new IllegalArgumentException(INVALID_IMAGE_PIXEL_MESSAGE);
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public long getSize() {
        return size;
    }

    public ImageType getImageType() {
        return imageType;
    }

    public Long getId() {
        return id;
    }
}
