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
            throw new IllegalArgumentException(String.format("이미지크기는 1MB(%s)를 초과할 수 없다", CAPACITY));
        }
    }

    private void validateWidth(int width) {
        if (width < WIDTH) {
            throw new IllegalArgumentException(String.format("이미지 width는 (%d)픽셀이상이어야 한다", WIDTH));
        }
    }

    private void validateHeight(int height) {
        if (height < HEIGHT) {
            throw new IllegalArgumentException(String.format("이미지 height는 (%d)픽셀이상이어야 한다", HEIGHT));
        }
    }

    private void validateRatio(int width, int height) {
        if (width / (double) height != RATIO) {
            throw new IllegalArgumentException(String.format("width와 height의 비율은 3:2(%s)여야 한다", RATIO));
        }
    }

}
