package nextstep.courses.domain.course.session.image;

import nextstep.courses.domain.BaseEntity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Image extends BaseEntity {
    public static final int MB = 1024 * 1024;
    public static final int WIDTH_MIN = 300;
    public static final int HEIGHT_MIN = 200;
    public static final double WIDTH_HEIGHT_RATIO = 1.5;

    private Long id;

    private int imageSize;

    private ImageType imageType;

    private int imageWidth;

    private int imageHeight;

    public Image(int imageSize, ImageType type, int imageWidth, int imageHeight, Long creatorId, LocalDateTime date) {
        this(0L, imageSize, type, imageWidth, imageHeight, creatorId, date, null);
    }

    public Image(Long id, int imageSize, ImageType imageType, int imageWidth, int imageHeight,
                 Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(creatorId, createdAt, updatedAt);
        checkImageSizeIsValid(imageSize);
        checkWidthAndHeightSizeIsValid(imageWidth, imageHeight);
        checkWidthAndHeightRatioIsValid(imageWidth, imageHeight);

        this.id = id;
        this.imageSize = imageSize;
        this.imageType = imageType;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public int getImageSize() {
        return imageSize;
    }

    public ImageType getImageType() {
        return imageType;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", imageSize=" + imageSize +
                ", imageType=" + imageType +
                ", imageWidth=" + imageWidth +
                ", imageHeight=" + imageHeight +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return imageSize == image.imageSize && imageWidth == image.imageWidth &&
                imageHeight == image.imageHeight && Objects.equals(id, image.id) && imageType == image.imageType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, imageSize, imageType, imageWidth, imageHeight);
    }
}
