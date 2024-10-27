package nextstep.session.image;

public class ImageSize {

    private static final String NOT_CORRECT_RATE = "이미지의 너비 높이가 3:2 비율이여야 합니다.";

    private final ImageWidth width;
    private final ImageHeight height;

    public ImageSize(int width, int height) {
        confirmRate(width, height);
        this.width = new ImageWidth(width);
        this.height = new ImageHeight(height);
    }

    private void confirmRate(int width, int height) {
        if (width * 2 != height * 3) {
            throw new IllegalArgumentException(NOT_CORRECT_RATE);
        }
    }
}
