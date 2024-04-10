package nextstep.sessions.domain.image;

public class Image {
    private static final int WIDTH = 300;
    private static final int HEIGHT = 200;
    private static final double RATIO = 1.5;
    private static final int CAPACITY = 1024 * 1024;

    private final int capacity;
    private final ImageType imageType;
    private final double width;

    private final double height;

    public Image(int capacity, ImageType imageType, int width, int height) {
        validateCapacity(capacity);
        validateWidth(width);
        validateHeight(height);
        validateRatio(width, height);

        this.capacity = capacity;
        this.imageType = imageType;
        this.width = width;
        this.height = height;
    }

    private void validateCapacity(int capacity) {
        if (capacity > CAPACITY) {
            throw new IllegalArgumentException("이미지크기는 1MB를 초과할 수 없다");
        }
    }

    private void validateWidth(int width) {
        if (width < WIDTH) {
            throw new IllegalArgumentException("이미지 width는 300픽셀이상이어야 한다");
        }
    }

    private void validateHeight(int height) {
        if (height < HEIGHT) {
            throw new IllegalArgumentException("이미지 height는 200픽셀이상이어야 한다");
        }
    }

    private void validateRatio(int width, int height) {
        if (width / (double) height != RATIO) {
            throw new IllegalArgumentException("width와 height의 비율은 3:2여야 한다");
        }
    }

    private double calculateRatio(int width, int height) {
        return width / (double) height;
    }

}
