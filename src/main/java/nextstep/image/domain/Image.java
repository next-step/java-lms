package nextstep.image.domain;

public class Image {

    private static final double ASPECT_RATIO = 1.5;

    public static final int MIN_WIDTH = 300;
    public static final int MIN_HEIGHT = 200;

    public static final long MAXIMUM_SIZE = 1024;

    private Long id;

    private int width;

    private int height;

    private ImageType imageType;

    private long size;

    public Image(Long id, int width, int height, ImageType imageType, long size) {
        this.id = id;
        this.width = width;
        this.height = height;
        this.imageType = imageType;
        this.size = size;
    }

    private Image(int width, int height, ImageType imageType, long size) {
        this(null, width, height, imageType, size);
    }

    public static Image of(int width, int height, ImageType imageType, long size) {
        checkImageSize(width, height, size);

        return new Image(width, height, imageType, size);
    }

    public Long getId() {
        return id;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public ImageType getImageType() {
        return imageType;
    }

    public long getSize() {
        return size;
    }

    private static void checkImageSize(int width, int height, long size) {
        if (width < MIN_WIDTH || height < MIN_HEIGHT) {
            throw new IllegalArgumentException("이미지의 width는 " + MIN_WIDTH + "픽셀, height는 " + MIN_HEIGHT + "픽셀 이상이어야 한다.");
        }

        if ((double) width / height != ASPECT_RATIO) {
            throw new IllegalArgumentException("이미지의 가로 세로 비율은 3:2 이어야_한다");
        }

        if (size > MAXIMUM_SIZE) {
            throw new IllegalArgumentException("이미지 크기는 1MB 이하여야 한다.");
        }
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", width=" + width +
                ", height=" + height +
                ", imageType=" + imageType +
                ", size=" + size +
                '}';
    }
}
