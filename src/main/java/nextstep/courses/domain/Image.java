package nextstep.courses.domain;

public class Image {
    private static final int ONE_MEGA_BYTE = 1048576;
    private static final int MIN_WIDTH = 300;
    private static final int MIN_HEIGHT = 200;

    private final int bytes;
    private final ImageType type;
    private final int width;
    private final int height;


    public Image(int bytes, ImageType type, int width, int height) {
        validCapacity(bytes);
        validSize(width, height);
        this.bytes = bytes;
        this.type = type;
        this.width = width;
        this.height = height;
    }

    private void validCapacity(int bytes) {
        if (bytes > ONE_MEGA_BYTE) {
            throw new IllegalArgumentException("이미지 용량은 1MB 이하");
        }
    }

    private void validSize(int width, int height) {
        if (width > MIN_WIDTH) {
            throw new IllegalArgumentException("이미지의 width는 300픽셀 이상");
        }
        if (height > MIN_HEIGHT) {
            throw new IllegalArgumentException("이미지의 height는 200픽셀 이상");
        }
        if (width * 2 != height * 3) {
            throw new IllegalArgumentException("이미지의 width:height 는 3:2");
        }
    }


}
