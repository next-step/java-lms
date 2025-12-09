package nextstep.courses.domain.image;

import nextstep.courses.InvalidImageFileException;

public class ImageDimension {
    private final static int MIN_WIDTH = 300; // 300 px
    private final static int MIN_HEIGHT = 200; // 200 px
    private final static double ASPECT_RATIO = 1.5; // width : height = 3 : 2

    private final int width;
    private final int height;

    public ImageDimension(int width, int height) {
        if (!isValidWidthAndHeight(width, height)) {
            throw new InvalidImageFileException(String.format("이미지는 가로 %spx 이상, 세로 %spx 이상, 가로 세로 비율이 %s:1 이어야 합니다.", MIN_WIDTH, MIN_HEIGHT, ASPECT_RATIO));
        }
        this.width = width;
        this.height = height;
    }

    private boolean isValidWidthAndHeight(int width, int height) {
        return width >= MIN_WIDTH && height >= MIN_HEIGHT && isValidRatio(width, height);
    }

    private boolean isValidRatio(int width, int height) {
        return (double) width / height == ASPECT_RATIO;
    }
}
