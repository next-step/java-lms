package nextstep.courses.domain;

import java.util.Objects;

public class ImageFile {
    private static final long MAX_SIZE = 1024 * 1024; // 1MB
    private static final int MIN_WIDTH_PX = 300;
    private static final int MIN_HEIGHT_PX = 200;
    private static final int WIDTH_RATIO = 3;
    private static final int HEIGHT_RATIO = 2;

    private Long id;
    private Long sessionId;
    private final long size;
    private final ImageType imageType;
    private final int width;
    private final int height;


    public ImageFile(long size) {
        this(null, 1L, size, "png", MIN_WIDTH_PX , MIN_HEIGHT_PX);
    }

    public ImageFile(long size, String imageType, int width, int height) {
        this(null, 1L, size, imageType, width, height);
    }

    public ImageFile(Long id, Long sessionId, long size, String imageType, int width, int height) {
        validateSize(size);
        validateDimensions(width, height);

        ImageType type = ImageType.getType(imageType);
        validateImageType(type);

        this.id = id;
        this.sessionId = sessionId;
        this.size = size;
        this.imageType = type;
        this.width = width;
        this.height = height;
    }

    public long getSize() {
        return this.size;
    }

    public ImageType getImageType() {
        return this.imageType;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public Long getImageId() {
        return this.id;
    }

    public void assignSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public Long getSessionId() {
        return this.sessionId;
    }

    private void validateImageType(ImageType type) {
        if (type == ImageType.UNKNOWN) {
            throw new IllegalArgumentException("지원하지 않는 이미지 타입입니다. (허용 형식: gif, jpg/jpeg, png, svg)");
        }
    }

    private void validateDimensions(int width, int height) {
        validateMinimumSize(width, height);
        validateAspectRatio(width, height);
    }

    private void validateMinimumSize(int width, int height) {
        if (width < MIN_WIDTH_PX) {
            throw new IllegalArgumentException("width 는 300픽셀 이상이여야합니다.");
        }
        if (height < MIN_HEIGHT_PX) {
            throw new IllegalArgumentException("height 는 200픽셀 이상이여야합니다.");
        }
    }

    private void validateAspectRatio(int width, int height) {
        if (width * HEIGHT_RATIO != height * WIDTH_RATIO) {
            throw new IllegalArgumentException("width 와 height 의 비율은 3:2여야 합니다.");
        }
    }

    private void validateSize(long size) {
        if (size > MAX_SIZE) {
            throw new IllegalArgumentException("이미지 파일 크기는 1MB 이하여야 합니다. (현재: " + size + ")");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ImageFile imageFile = (ImageFile) o;
        return size == imageFile.size && width == imageFile.width && height == imageFile.height && Objects.equals(id, imageFile.id) && imageType == imageFile.imageType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, size, imageType, width, height);
    }

    @Override
    public String toString() {
        return "ImageFile{" +
                "id=" + id +
                ", size=" + size +
                ", imageType=" + imageType +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
