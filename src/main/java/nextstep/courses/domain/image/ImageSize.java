package nextstep.courses.domain.image;

import nextstep.courses.ImageDimensionException;

public class ImageSize {
    public static final int IMAGE_MIN_WIDTH = 300;
    public static final int IMAGE_MIN_HEIGHT = 200;
    private final int width;
    private final int height;

    public ImageSize(int width, int height) {
        validateDimension(width, height);

        this.width = width;
        this.height = height;
    }

    public void validateDimension(int width, int height) {
        validateWidth(width);
        validateHeight(height);
    }

    private void validateWidth(int width) {
        if (width < IMAGE_MIN_WIDTH) {
            throw new ImageDimensionException("이미지의 가로는 300을 넘어야합니다.");
        }
    }

    private void validateHeight(int height) {
        if (height < IMAGE_MIN_HEIGHT) {
            throw new ImageDimensionException("이미지의 세로는 200을 넘어야합니다.");
        }
    }
}
