package nextstep.image.domain;

public class ImageHeight {
    private int height;

    public ImageHeight(int height) {
        validateHeight(height);
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    private void validateHeight(final int height) {
        if (height < 200) {
            throw new IllegalArgumentException("이미지 높이는 200px보다 커야 합니다.");
        }
    }
}
