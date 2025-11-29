package nextstep.courses.domain.image;

public class ImageDimentsion {

    public static final int MIN_WIDTH = 300;
    public static final int MIN_HEIGHT = 200;
    public static final double ASPECT_RATIO = 1.5;

    private final int width;
    private final int height;

    public ImageDimentsion(int width, int height) {
        validateSize(width, height);
        validateRatio(width, height);
        this.width = width;
        this.height = height;
    }

    private void validateRatio(int width, int height) {
        double ratio = (double) width / height;
        if (ratio != ASPECT_RATIO) {
            throw new IllegalArgumentException("3:2 비율로 다시 업로드 해주세요.");
        }
    }

    private void validateSize(int width, int height) {
        if (isSize(width, height)) {
            throw new IllegalArgumentException("너비는 300px, 높이는 200px 이상이어야 합니다.");
        }
    }

    private static boolean isSize(int width, int height) {
        return width < MIN_WIDTH || height < MIN_HEIGHT;
    }
}
