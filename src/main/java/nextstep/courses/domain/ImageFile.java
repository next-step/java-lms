package nextstep.courses.domain;

public class ImageFile {
    private static final long MAX_SIZE = 1024 * 1024; // 1MB
    private static final int MIN_WIDTH_PX = 300;
    private static final int MIN_HEIGHT_PX = 200;
    private static final int WIDTH_RATIO = 3;
    private static final int HEIGHT_RATIO = 2;

    private long size;
    private ImageType imageType;
    private int width;
    private int height;


    public ImageFile(long size) {
        this(size, "png", 300, 200);
    }

    public ImageFile(long size, String imageType, int width, int height) {
        validateSize(size);
        validateDimensions(width, height);

        ImageType type = ImageType.getType(imageType);
        validateImageType(type);

        this.size = size;
        this.imageType = type;
        this.width = width;
        this.height = height;
    }

    private void validateImageType(ImageType type) {
        if (type == ImageType.UNKNOWN) {
            throw new IllegalArgumentException("지원하지 않는 이미지 타입입니다. (허용 형식: gif, jpg/jpeg, png, svg)");
        }
    }

    private void validateDimensions(int width, int height) {
        validateMinimumSize(width, height);
        validateAspectRatio(width, height);
    }

    private void validateMinimumSize(int width, int height) {
        if (width < MIN_WIDTH_PX) {
            throw new IllegalArgumentException("width 는 300픽셀 이상이여야합니다.");
        }
        if (height < MIN_HEIGHT_PX) {
            throw new IllegalArgumentException("height 는 200픽셀 이상이여야합니다.");
        }
    }

    private void validateAspectRatio(int width, int height) {
        if (width * HEIGHT_RATIO != height * WIDTH_RATIO) {
            throw new IllegalArgumentException("width 와 height 의 비율은 3:2여야 합니다.");
        }
    }

    private void validateSize(long size) {
        if (size > MAX_SIZE) {
            throw new IllegalArgumentException("이미지 파일 크기는 1MB 이하여야 합니다. (현재: " + size + ")");
        }
    }
}
