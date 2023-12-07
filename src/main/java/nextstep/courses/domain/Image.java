package nextstep.courses.domain;

public class Image {
    private static final int SIZE_MAX = 1048576;
    private static final int WIDTH_MIN = 300;
    private static final int HEIGHT_MIN = 200;

    private final Long id;
    private String title;
    private long imageSize;
    private ImageType imageType;
    private int width;
    private int height;

    public Image(String title, long imageSize, String imageType, int width, int height) {
        this(0L, title, imageSize, imageType, width, height);
    }

    public Image(Long id, String title, long imageSize, String imageType, int width, int height) {
        if (imageSize > SIZE_MAX) {
            throw new IllegalArgumentException("이미지 크기는 1MB 이하여야 합니다.");
        }
        if (width < WIDTH_MIN || height < HEIGHT_MIN || isNotValidRatio(width, height)) {
            throw new IllegalArgumentException("이미지의 width는 300픽셀, height는 200픽셀 이상이어야 하며 width와 height의 비율은 3:2여야 합니다.");
        }

        this.id = id;
        this.title = title;
        this.imageSize = imageSize;
        this.imageType = ImageType.valueOf(imageType.toUpperCase());
        this.width = width;
        this.height = height;
    }

    public Image(Long id) {
        this.id = id;
    }

    public static Image newImage(Long imageId) {
        return new Image(imageId);
    }

    private boolean isNotValidRatio(int width, int height) {
        return width * 2 != height * 3;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public long getImageSize() {
        return imageSize;
    }

    public ImageType getImageType() {
        return imageType;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
