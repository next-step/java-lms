package nextstep.image.domain;

public class Image {

    public static final int MIN_WIDTH = 300;
    public static final int MIN_HEIGHT = 200;

    private final int width;

    private final int height;

    private final ImageType imageType;

    private final long size;

    private Image(int width, int height, ImageType imageType, long size) {
        this.width = width;
        this.height = height;
        this.imageType = imageType;
        this.size = size;
    }

    public static Image of(int width, int height, ImageType imageType, long size) {
        if (size > 1024) {
            throw new IllegalArgumentException("이미지 크기는 1MB 이하여야 한다.");
        }
        return new Image(width, height, imageType, size);
    }
}
