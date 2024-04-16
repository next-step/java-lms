package nextstep.sessions.domain.image;

public class Image {

    private final Capacity capacity;
    private final ImageType imageType;
    private final ImageSize size;

    public Image(int capacity, ImageType imageType, ImageSize size) {
        this(new Capacity(capacity), imageType, size);
    }

    public Image(Capacity capacity, ImageType imageType, ImageSize size) {
        this.capacity = capacity;
        this.imageType = imageType;
        this.size = size;
    }

    public int getCapacity() {
        return capacity.getCapacity();
    }

    public String getImageType() {
        return imageType.name();
    }

    public double getWidth() {
        return size.getWidth();
    }

    public double getHeight() {
        return size.getHeight();
    }
}
