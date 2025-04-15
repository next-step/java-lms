package nextstep.sessions.domain;

public class Image {
    private static final Long MAX_SIZE_BYTES = 1_048_576L;
    private static final Float MIN_SIZE_WIDTH = 300F;
    private static final Float MIN_SIZE_HEIGHT = 200F;

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
    }

    private void validateSize(Long sizeInBytes) {
        if (sizeInBytes > MAX_SIZE_BYTES) {
            throw new IllegalArgumentException("이미지 크기는 최대 1MB 이하여야 합니다.");
        }
    }
}
