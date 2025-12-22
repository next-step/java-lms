package nextstep.courses.domain.image;

public class ImageDimension {
    private static final int MIN_WIDTH = 300;
    private static final int MIN_HEIGHT = 200;
    private static final int WIDTH_RATIO = 3;
    private static final int HEIGHT_RATIO = 2;


    private final int width;
    private final int height;

    public ImageDimension(int width, int height) {
        validateSize(width, height);
        validateRatio(width, height);
        this.width = width;
        this.height = height;
    }

    private void validateRatio(int width, int height) {
        if (width * HEIGHT_RATIO != height * WIDTH_RATIO) {
            throw new IllegalArgumentException("이미지 비율은 3:2여야 합니다.");
        }
    }

    private void validateSize(int width, int height) {
        if (width < MIN_WIDTH) {
            throw new IllegalArgumentException("width는 300픽셀 이상이어야 한다.");
        }
        if (height < MIN_HEIGHT) {
            throw new IllegalArgumentException("height는 200픽셀 이상이어야 한다.");
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
