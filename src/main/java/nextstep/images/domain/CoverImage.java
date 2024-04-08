package nextstep.images.domain;

public class CoverImage {

    private static final int MAX_SIZE = 1;
    private static final int MIN_WIDTH = 300;

    private double size;

    private int width;

    public CoverImage(final double size) {
        validateSize(size);
        this.size = size;
    }

    public CoverImage(final double size, final int width) {
        validateSize(size);
        validateWidth(width);
        this.size = size;
        this.width = width;
    }

    private void validateSize(final double size) {
        if (size > MAX_SIZE) {
            throw new IllegalArgumentException("이미지 크기는 1MB를 넘을 수 없습니다.");
        }
    }

    private void validateWidth(final int width) {
        if (width < MIN_WIDTH) {
            throw new IllegalArgumentException("이미지의 width는 300픽셀 미만일 수 없습니다.");
        }
    }
}
