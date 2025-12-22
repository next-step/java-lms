package nextstep.sessions.domain.image;

public class ImageDimension {

    private static final int MIN_WIDTH = 300;
    private static final int MIN_HEIGHT = 200;

    private static final String ERROR_MIN_SIZE = "이미지 크기가 최소 조건을 만족하지 않습니다";
    private static final String ERROR_RATIO = "이미지 비율은 3:2여야 합니다";

    private final int width;
    private final int height;

    public ImageDimension(int width, int height) {
        validate(width, height);
        this.width = width;
        this.height = height;
    }

    private void validate(int width, int height) {
        if (width < MIN_WIDTH || height < MIN_HEIGHT) {
            throw new IllegalArgumentException(ERROR_MIN_SIZE);
        }
        if (width * 2 != height * 3) {
            throw new IllegalArgumentException(ERROR_RATIO);
        }
    }

}
