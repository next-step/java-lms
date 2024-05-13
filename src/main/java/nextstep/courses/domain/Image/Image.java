package nextstep.courses.domain.Image;

public class Image {
    private final ImageCapacity capacity;
    private final ImageType type;
    private final ImageSize size;

    public Image(ImageCapacity capacity, ImageType type, ImageSize size) {
        this.capacity = capacity;
        this.type = type;
        this.size = size;
    }
}
