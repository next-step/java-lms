package nextstep.courses.domain;

public class ImageSize {

    private static final int MIN_WIDTH = 300;
    private static final int MIN_HEIGHT = 200;
    private static final double RATIO = (double) Math.round(3.0 / 2.0 * 100) / 100;
    private final int width;
    private final int height;

    public ImageSize(int width, int height) {
        validate(width, height);
        this.width = width;
        this.height = height;
    }

    private void validate(int width, int height) {
        if (width < MIN_WIDTH || height < MIN_HEIGHT) {
            throw new IllegalArgumentException(
                    String.format("강의 커버 이미지의 최소 가로길이 %d, 세로길이 %d 이상이여야 합니다.", MIN_WIDTH, MIN_HEIGHT));
        }
        if ((double) Math.round((double) width / height * 100) / 100 != RATIO) {
            throw new IllegalArgumentException(String.format("강의 커버 이미지의 비율은 %f 올바르지 않습니다.", RATIO));
        }
    }
}
