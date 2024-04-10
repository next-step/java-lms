package nextstep.courses.domain;

public class SessionCoverImage {

    public static final int MAX_SIZE = 1_048_576;
    public static final int MIN_WIDTH = 300;
    public static final int MIN_HEIGHT = 200;

    private final int sizeOfBytes;
    private final ImageType type;
    private final int width;
    private final int height;

    public SessionCoverImage(int sizeOfBytes, ImageType type, int width, int height) {
        validateLessEqualThen1MB(sizeOfBytes);
        validateWidthAndHeight(width, height);
        validateRatioThreeToTwo(width, height);
        this.sizeOfBytes = sizeOfBytes;
        this.type = type;
        this.width = width;
        this.height = height;
    }

    private void validateRatioThreeToTwo(int width, int height) {
        if (width * 2 != height * 3) {
            throw new IllegalArgumentException("가로 크기와 높이의 비율은 3:2 여야 합니다.");
        }
    }

    private void validateWidthAndHeight(int width, int height) {
        if (width < MIN_WIDTH || height < MIN_HEIGHT) {
            throw new IllegalArgumentException("가로 크기는 300px 이상, 높이는 200px 이상이어야 합니다.");
        }
    }

    private void validateLessEqualThen1MB(int sizeOfBytes) {
        if (sizeOfBytes > MAX_SIZE) {
            throw new IllegalArgumentException("이미지 용량은 1MB 이하여야 합니다.");
        }
    }

    public int getSizeOfBytes() {
        return sizeOfBytes;
    }

    public ImageType getType() {
        return type;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
