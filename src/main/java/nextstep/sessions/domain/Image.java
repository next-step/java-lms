package nextstep.sessions.domain;

public class Image {
    private static final Long MAX_SIZE_BYTES = 1024 * 1024L;
    private static final Float MIN_SIZE_WIDTH = 300F;
    private static final Float MIN_SIZE_HEIGHT = 200F;
    private static final Float ASPECT_RATIO_TOLERANCE = 0.01f;

    private Long id;
    private Long sizeInBytes;
    private String title;
    private ImageType type;
    private Float width;
    private Float height;

    public Image(Long sizeInBytes, String title, ImageType type, Float width, Float height) {
        this(sizeInBytes, 0L, title, type, width, height);
    }

    public Image(Long sizeInBytes, Long id, String title, ImageType type, Float width, Float height) {
        validate(sizeInBytes, title, type, width, height);
        this.sizeInBytes = sizeInBytes;
        this.id = id;
        this.title = title;
        this.type = type;
        this.width = width;
        this.height = height;
    }

    private void validate(Long sizeInBytes, String title, ImageType type, Float width, Float height) {
        validateSize(sizeInBytes);
        validateWidth(width);
        validateHeight(height);
        validateAspectRatio(width, height);
    }

    private void validateSize(Long sizeInBytes) {
        if (sizeInBytes > MAX_SIZE_BYTES) {
            throw new IllegalArgumentException("이미지 크기는 최대 1MB 이하여야 합니다.");
        }
    }

    private void validateWidth(Float width) {
        if (width < MIN_SIZE_WIDTH) {
            throw new IllegalArgumentException("이미지 가로 길이는 최소 300픽셀 이상이어야 합니다.");
        }
    }

    private void validateHeight(Float height) {
        if (height < MIN_SIZE_HEIGHT) {
            throw new IllegalArgumentException("이미지 세로 길이는 최소 200픽셀 이상이어야 합니다.");
        }
    }

    private void validateAspectRatio(float width, float height) {
        float ratio = width / height;
        if (Math.abs(ratio - (MIN_SIZE_WIDTH / MIN_SIZE_HEIGHT)) > ASPECT_RATIO_TOLERANCE) {
            throw new IllegalArgumentException("이미지 비율은 3:2이어야 합니다.");
        }
    }
}
