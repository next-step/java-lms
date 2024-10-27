package nextstep.session.image;

public class Image {

    private final Long id;
    private final String name;
    private final ImageSize size;
    private final ImageCapacity capacity;

    public Image(Long id, String name, int width, int height, int capacity) {
        ImageExtension.confirmImageExtension(name);
        this.id = id;
        this.name = name;
        this.size = new ImageSize(width, height);
        this.capacity = new ImageCapacity(capacity);
    }

}
