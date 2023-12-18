package nextstep.courses.domain;

public class Image {
    private static final int MAX_SIZE = 1;
    private static final int MAX_WIDTH = 300;
    private static final int MAX_HEIGHT = 200;
    private final int size;
    private final ImageType imageType;
    private final int width;
    private final int heigth;
    public Image(int size, String type, int width, int height) {
        validateSize(size);
        validateWidthAndHeight(width, height);
        this.size = size;
        this.imageType = ImageType.of(type);
        this.width = width;
        this.heigth = height;
    }

    private void validateWidthAndHeight(int width, int height) {
        if (width < MAX_WIDTH || height < MAX_HEIGHT) {
            throw new IllegalArgumentException("이미지의 width는 300픽셀, height는 200픽셀 이상이어야합니다");
        }
    }

    private void validateSize(int size) {
        if (size > MAX_SIZE) {
            throw new IllegalArgumentException("이미지 크기는 1MB 이하여야 한다");
        }
    }
}
