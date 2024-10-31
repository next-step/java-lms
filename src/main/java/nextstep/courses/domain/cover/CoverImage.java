package nextstep.courses.domain.cover;

public class CoverImage {
    private final Long id;
    private final int imageSize;
    private final ImageExtension extension;
    private final int width;
    private final int height;

    private static final int MAX_IMAGE_SIZE_MB = 1;
    private static final int MIN_WIDTH = 300;
    private static final int MIN_HEIGHT = 200;
    private static final double ASPECT_RATIO = 3.0 / 2.0;

    private CoverImage(Long id, int imageSize, String extension, int width, int height) {
        validateImage(imageSize, extension, width, height);

        this.id = id;
        this.imageSize = imageSize;
        this.extension = ImageExtension.getExtension(extension);
        this.width = width;
        this.height = height;
    }

    public static CoverImage of(int imageSize, String extension, int width, int height) {
        return new CoverImage(0L, imageSize, extension, width, height);
    }

    private void validateImage(int imageSize, String extension, int width, int height) {
        if (isInvalidImageSize(imageSize)) {
            throw new IllegalArgumentException("이미지 크기는 1MB 이하여야 합니다.");
        }
        if (isInvalidImageExtension(extension)) {
            throw new IllegalArgumentException("허용되지 않는 이미지 형식입니다.");
        }
        if (isInvalidImageSize(width, height)) {
            throw new IllegalArgumentException("이미지의 크기와 비율이 유효하지 않습니다.");
        }
    }

    private boolean isInvalidImageSize(int imageSize) {
        return imageSize > MAX_IMAGE_SIZE_MB * 1024 * 1024;
    }

    private Boolean isInvalidImageExtension(String extension) {
        return ImageExtension.isInvalidImageExtension(extension);
    }

    private boolean isInvalidImageSize(int width, int height) {
        return width < MIN_WIDTH || height < MIN_HEIGHT || (double) width / height != ASPECT_RATIO;
    }

    public Long getId() {
        return id;
    }

    public int getImageSize() {
        return imageSize;
    }

    public ImageExtension getExtension() {
        return extension;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}