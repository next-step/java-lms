package nextstep.sessions.domain.image;

public class ImageSize {

    static final String ERROR_IMAGE_SIZE = "이미지 용량은 1MB 이하여야 합니다";
    private static final long MAX_SIZE = 1_000_000;

    private final long size;

    public ImageSize(long size) {
        validate(size);
        this.size = size;
    }

    private static void validate(long size) {
        if (size <= 0 || size > MAX_SIZE) {
            throw new IllegalArgumentException(ERROR_IMAGE_SIZE);
        }
    }

    public long value() {
        return size;
    }
}
