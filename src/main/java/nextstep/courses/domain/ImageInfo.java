package nextstep.courses.domain;

public class ImageInfo {

    public static final int SIZE_LIMIT = 1048576;
    public static final int WIDTH_LIMIT = 300;
    public static final int HEIGHT_LIMIT = 200;

    private Long id;
    private String title;
    private long imageSize;
    private ImageType imageType;
    private final int width;
    private final int height;

    public ImageInfo(Long id, String title, long imageSize, String imageType, int width, int height) {
        if (imageSize > SIZE_LIMIT) {
            throw new IllegalArgumentException("이미지 크기는 1MB 이하여야 합니다.");
        }
        if (ImageType.isValidType(imageType)) {
            throw new IllegalArgumentException("이미지 타입은 gif, jpg(jpeg 포함), png, svg만 허용됩니다.");
        }
        if (width < WIDTH_LIMIT || height < HEIGHT_LIMIT || !isValidRatio()) {
            throw new IllegalArgumentException("이미지의 width는 300픽셀, height는 200픽셀 이상이어야 하며, width와 height의 비율은 3:2여야 합니다.");
        }

        this.id = id;
        this.title = title;
        this.imageSize = imageSize;
        this.imageType = ImageType.valueOf(imageType);
        this.width = width;
        this.height = height;
    }

    private boolean isValidRatio() {
        return (double) this.width / this.height == 1.5;
    }
}
