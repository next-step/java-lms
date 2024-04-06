package nextstep.courses.domain;

public class SessionCoverImage {

    public static final int MAX_SIZE = 1_048_576;

    private final int sizeOfBytes;
    private final ImageType type;
    private final int width;
    private final int height;

    public SessionCoverImage(int sizeOfBytes, ImageType type, int width, int height) {
        validateLessEqualThen1MB(sizeOfBytes);
        this.sizeOfBytes = sizeOfBytes;
        this.type = type;
        this.width = width;
        this.height = height;
    }

    private void validateLessEqualThen1MB(int sizeOfBytes) {
        if (sizeOfBytes > MAX_SIZE) {
            throw new IllegalArgumentException("이미지 용량은 1MB 이하여야 합니다.");
        }
    }
}
