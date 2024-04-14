package nextstep.sessions.domain.image;

public class ImageSize {

    private static final int WIDTH = 300;
    private static final int HEIGHT = 200;
    private static final double RATIO = 1.5;

    private final double width;

    private final double height;

    public ImageSize(double width, double height) {
        validateWidth(width);
        validateHeight(height);
        validateRatio(width, height);
        this.width = width;
        this.height = height;
    }

    private void validateRatio(double width, double height) {
        if (width / height != RATIO) {
            throw new IllegalArgumentException(String.format("width와 height의 비율은 3:2(%s)여야 한다", RATIO));
        }
    }

    private void validateHeight(double height) {
        if (height < HEIGHT) {
            throw new IllegalArgumentException(String.format("이미지 height는 (%d)픽셀이상이어야 한다", HEIGHT));
        }
    }

    private void validateWidth(double width) {
        if (width < WIDTH) {
            throw new IllegalArgumentException(String.format("이미지 width는 (%d)픽셀이상이어야 한다", WIDTH));
        }
    }

}
