package nextstep.courses.domain.course.image;

import java.time.LocalDateTime;

public class Image {
    public static final int MB = 1024 * 1024;
    public static final int WIDTH_MIN = 300;
    public static final int HEIGHT_MIN = 200;
    public static final double WIDTH_HEIGHT_RATIO = 1.5;

    private Long id;

    private int imageSize;

    private Type type;

    private int imageWidth;

    private int imageHeight;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Image() {
    }

    public Image(int imageSize, String type, int imageWidth, int imageHeight) {
        this(0L, imageSize, type, imageWidth, imageHeight, LocalDateTime.now(), null);
    }

    public Image(Long id, int imageSize, String type, int imageWidth, int imageHeight,
                 LocalDateTime createdAt, LocalDateTime updatedAt) {
        checkImageSizeIsValid(imageSize);
        checkWidthAndHeightSizeIsValid(imageWidth, imageHeight);
        checkWidthAndHeightRatioIsValid(imageWidth, imageHeight);

        this.id = id;
        this.imageSize = imageSize;
        this.type = Type.find(type);
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    private static void checkImageSizeIsValid(int imageSize) {
        if (imageSize > 1 * MB) {
            throw new IllegalArgumentException("사진 크기는 1MB를 넘을 수 없습니다.");
        }
    }

    private static void checkWidthAndHeightSizeIsValid(int imageWidth, int imageHeight) {
        if (imageWidth < WIDTH_MIN || imageHeight < HEIGHT_MIN) {
            throw new IllegalArgumentException(
                    String.format("가로 픽셀은 %d, 세로 픽셀은 %d 이상이어야 합니다.", WIDTH_MIN, HEIGHT_MIN)
            );
        }
    }

    private static void checkWidthAndHeightRatioIsValid(int imageWidth, int imageHeight) {
        if ((double) imageWidth / imageHeight != WIDTH_HEIGHT_RATIO) {
            throw new IllegalArgumentException("가로 세로 비율은 3:2여야 합니다.");
        }
    }
}
