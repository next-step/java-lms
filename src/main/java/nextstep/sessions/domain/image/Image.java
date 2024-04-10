package nextstep.sessions.domain.image;

public class Image {
    private static final int CAPACITY = 1024 * 1024;

    private final int capacity;
    private final ImageType imageType;
    private final ImageSize size;

    public Image(int capacity, ImageType imageType, int width, int height) {
        this(capacity, imageType, new ImageSize(width, height));
    }

    public Image(int capacity, ImageType imageType, ImageSize size) {
        validateCapacity(capacity);
        this.capacity = capacity;
        this.imageType = imageType;
        this.size = size;
    }

    private void validateCapacity(int capacity) {
        if (capacity > CAPACITY) {
            throw new IllegalArgumentException(String.format("이미지크기는 1MB(%s)를 초과할 수 없다", CAPACITY));
        }
    }

}
