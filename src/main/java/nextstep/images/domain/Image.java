package nextstep.images.domain;

public class Image {
    public static final long MAX_IMAGE_SIZE = 1024;
    public static final int MIN_WIDTH_SIZE = 300;
    public static final int MIN_HEIGHT_SIZE = 200;
    public static final String RATIO = "3:2";

    private final Long id;
    private final long size;
    private final int width;
    private final int height;
    private final ImageType imageType;

    public Image(Long id, long size, int width, int height, ImageType imageType) {
        validateImage(size, width, height);
        this.id = id;
        this.size = size;
        this.width = width;
        this.height = height;
        this.imageType = imageType;
    }

    private void validateImage(long size, int width, int height) {
        validateSize(size);
        validateWidth(width);
        validateHeight(height);
        validateRatio(width, height);
    }

    private void validateRatio(int width, int height) {
        String[] ratio = RATIO.split(":");
        if (width * Integer.parseInt(ratio[1]) != height * Integer.parseInt(ratio[0])) {
            throw new IllegalArgumentException("비율은 가로:세로 " + RATIO + "여야 합니다.");
        }
    }

    private void validateHeight(int height) {
        if (height < MIN_HEIGHT_SIZE) {
            throw new IllegalArgumentException("세로는 최소 " + MIN_HEIGHT_SIZE + "이상이어야 합니다.");
        }
    }

    private void validateWidth(int width) {
        if (width < MIN_WIDTH_SIZE) {
            throw new IllegalArgumentException("가로는 최소 " + MIN_WIDTH_SIZE + "이상이어야 합니다.");
        }
    }

    private void validateSize(long size) {
        if (size > MAX_IMAGE_SIZE) {
            throw new IllegalArgumentException("이미지 크기는 " + MAX_IMAGE_SIZE + "Byte를 초과할 수 없습니다.");
        }
    }

}
