package nextstep.courses.domain.image;

import nextstep.courses.ImageDimensionException;

public class ImageSize {
    public static final int IMAGE_MIN_WIDTH = 300;
    public static final int IMAGE_MIN_HEIGHT = 200;
    public static final int WIDTH_RATIO = 3;
    public static final int HEIGHT_RATIO = 2;

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
        validateRatio(width, height);
    }

    private void validateWidth(int width) {
        if (width < IMAGE_MIN_WIDTH) {
            throw new ImageDimensionException(String.format("이미지의 가로는 %d을 넘어야합니다.",  IMAGE_MIN_WIDTH));
        }
    }

    private void validateHeight(int height) {
        if (height < IMAGE_MIN_HEIGHT) {
            throw new ImageDimensionException(String.format("이미지의 세로는 %d을 넘어야합니다.", IMAGE_MIN_HEIGHT));
        }
    }

    private void validateRatio(int width, int height) {
        if (width * HEIGHT_RATIO != height * WIDTH_RATIO) {
            throw new ImageDimensionException(String.format("이미지의 가로, 세로 비율은 %d:%d여야합니다.", WIDTH_RATIO, HEIGHT_RATIO));
        }
    }
}
