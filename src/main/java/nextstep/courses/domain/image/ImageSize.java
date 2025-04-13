package nextstep.courses.domain.image;

public class ImageSize {
    private static final int MIN_WIDTH = 300;
    private static final int MIN_HEIGHT = 200;
    private static final int ASPECT_RATIO_WIDTH = 3;
    private static final int ASPECT_RATIO_HEIGHT = 2;

    private final int width;
    private final int height;

    public ImageSize(int width, int height) {
        validateDimensions(width, height);
        this.width = width;
        this.height = height;
    }

    private void validateDimensions(int width, int height) {
        if (width < MIN_WIDTH || height < MIN_HEIGHT) {
            throw new IllegalArgumentException("이미지는 최소 300x200 픽셀 이상이어야 합니다.");
        }
        
        if (width * ASPECT_RATIO_HEIGHT != height * ASPECT_RATIO_WIDTH) {
            throw new IllegalArgumentException("이미지는 3:2 비율이어야 합니다.");
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
} 