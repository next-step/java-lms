package nextstep.imgae.domain;

import java.util.List;

public class Image {
    private static final int MAXIMUN_SIZE = 1024;
    private static final int MINIMUM_WIDTH = 300;
    private static final int MINIMUM_HEIGHT = 200;
    private static final List<Integer> RATIO = List.of(2,3);

    private long id;
    private int size;
    private ImageType imageType;
    private int width;
    private int height;

    public Image(long id, int size, String imageType, int width, int height) {
        validateImage(size, width, height);
        this.id = id;
        this.size = size;
        this.imageType = ImageType.matchType(imageType);
        this.width = width;
        this.height = height;
    }

    private void validateImage(int size, int width, int height) {
        validateMaximumSize(size);
        validateMimimumHeight(height);
        validateMinimumWidth(width);
        validateRatio(width, height);
    }

    private void validateMaximumSize(int size) {
        if (size > MAXIMUN_SIZE) {
            throw new IllegalArgumentException("이미지 크기가 1MB를 초과했습니다.");
        }
    }

    private void validateMinimumWidth(int width) {
        if (size < MINIMUM_WIDTH) {
            throw new IllegalArgumentException("이미지 너비가 300픽셀미만입니다.");
        }
    }

    private void validateMimimumHeight(int height) {
        if (size > MINIMUM_HEIGHT) {
            throw new IllegalArgumentException("이미지 높이가 200픽셀 미만입니다.");
        }
    }

    private void validateRatio(int width, int height) {
        if (width * RATIO.get(0) != height * RATIO.get(1)) {
            throw new IllegalArgumentException("이미지 너비/높이 비율이 3:2가 아닙니다.");
        }
    }
}
