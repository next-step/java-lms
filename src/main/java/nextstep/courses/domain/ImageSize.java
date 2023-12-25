package nextstep.courses.domain;

public class ImageSize {
    private static final int MAX_SIZE = 1;
    private static final int MAX_WIDTH = 300;
    private static final int MAX_HEIGHT = 200;
    private final int size;
    private final int width;
    private final int height;

    public ImageSize(int size, int width, int height) {
        validateSize(size);
        validateWidthAndHeight(width, height);
        this.size = size;
        this.width = width;
        this.height = height;
    }

    private void validateWidthAndHeight(int width, int height) {
        if (width < MAX_WIDTH || height < MAX_HEIGHT) {
            throw new IllegalArgumentException("이미지의 width는 300픽셀, height는 200픽셀 이상이어야합니다");
        }
        if (width * 2 != height * 3) {
            throw new IllegalArgumentException("width와 height의 비율dl 3:2 비율이 아닙니다");
        }
    }

    private void validateSize(int size) {
        if (size > MAX_SIZE) {
            throw new IllegalArgumentException("이미지 크기는 1MB 이하여야 한다");
        }
    }

    public int getSize() {
        return size;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
