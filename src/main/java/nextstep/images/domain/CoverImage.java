package nextstep.images.domain;

public class CoverImage {

    private static final int MAX_SIZE = 1;
    private static final int MIN_WIDTH = 300;
    private static final int MIN_HEIGHT = 200;
    private static final int WIDTH_RATIO = 3;
    private static final int HEIGHT_RATIO = 2;

    private Long id;

    private double size;

    private int width;

    private int height;

    private Long sessionId;

    private CoverImageType coverImageType;

    public CoverImage(final double size, final int width, final int height, final String type) {
        validate(size, width, height);
        this.size = size;
        this.width = width;
        this.height = height;
        this.coverImageType = CoverImageType.from(type);
    }

    private void validate(final double size, final int width, final int height) {
        validateSize(size);
        validateWidth(width);
        validateHeight(height);
        validateRate(width, height);
    }

    private void validateSize(final double size) {
        if (size > MAX_SIZE) {
            throw new IllegalArgumentException("현재 이미지 크기는 " + size + "MB 입니다. "
                    + "이미지 크기는 " + MAX_SIZE + "MB를 넘을 수 없습니다.");
        }
    }

    private void validateWidth(final int width) {
        if (width < MIN_WIDTH) {
            throw new IllegalArgumentException("현재 이미지의 width는 " + width + "입니다. "
                    + "이미지의 width는 " + MIN_WIDTH + "픽셀 미만일 수 없습니다.");
        }
    }

    private void validateHeight(final int height) {
        if (height < MIN_HEIGHT) {
            throw new IllegalArgumentException("현재 이미지의 height는 " + height + "입니다. "
                    + "이미지의 height는 " + MIN_HEIGHT + "픽셀 미만일 수 없습니다.");
        }
    }

    private void validateRate(final int width, final int height) {
        if (HEIGHT_RATIO * width != WIDTH_RATIO * height) {
            throw new IllegalArgumentException("이미지의 width와 heigth의 비율은 " + WIDTH_RATIO
                    + ":" + HEIGHT_RATIO + "여야 합니다.");
        }
    }

    public double getSize() {
        return size;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getCoverImageType() {
        return coverImageType.name();
    }
}
