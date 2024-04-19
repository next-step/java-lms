package nextstep.sessions.domain.image;

public class Image {
    private final Long id;
    private final Capacity capacity;
    private final ImageType imageType;
    private final ImageSize size;

    public static Image createImageWithImageType(ImageType imageType) {
        return new Image(1L, new Capacity(0), imageType, new ImageSize(300, 200));
    }

    public Image(int capacity, ImageType imageType, ImageSize size) {
        this(new Capacity(capacity), imageType, size);
    }

    public Image(Capacity capacity, ImageType imageType, ImageSize size) {
        this(null, capacity, imageType, size);
    }

    public Image(Long id, Capacity capacity, ImageType imageType, ImageSize size) {
        this.id = id;
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

    public long getId() {
        return id;
    }
}
