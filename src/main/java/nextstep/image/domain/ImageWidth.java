package nextstep.image.domain;

public class ImageWidth {
    private int width;

    public ImageWidth(int width) {
        validateWidth(width);
        this.width = width;
    }

    public int getWidth() {
        return width;
    }

    private void validateWidth(final int width) {
        if (width < 300) {
            throw new IllegalArgumentException("이미지 너비는 300px보다 커야 합니다.");
        }
    }
}
