package nextstep.courses.domain.image;

import nextstep.courses.ImageDimensionException;

public class ImageSize {
    public static final int IMAGE_MAX_WIDTH = 300;
    private final int width;
    private final int height;

    public ImageSize(int width, int height) {
        validateWidth(width);

        this.width = width;
        this.height = height;
    }

    private void validateWidth(int width) {
        if (width > IMAGE_MAX_WIDTH) {
            throw new ImageDimensionException("이미지의 가로는 300을 넘을 수 없습니다.");
        }
    }
}
