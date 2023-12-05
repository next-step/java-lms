package nextstep.courses.domain.image;

public class Image {

    private final Long id;
    private final ImageName name;
    private final ImageSize size;
    private final ImagePixel pixel;
    private final Long sessionId;

    public Image(Long id, ImageName name, ImageSize size, ImagePixel pixel, Long sessionId) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.pixel = pixel;
        this.sessionId = sessionId;
    }

    public Image(ImageName name, ImageSize size, ImagePixel pixel, Long sessionId) {
        this(null, name, size, pixel, sessionId);
    }
    public Image(Image image) {
        this(image.id, image.name, image.size, image.pixel, image.sessionId);
    }

    public Long id() {
        return id;
    }

    public String name() {
        return name.name();
    }

    public int volume() {
        return size.volume();
    }

    public int height() {
        return pixel.height();
    }

    public int width() {
        return pixel.width();
    }

    public Long sessionId() {
        return sessionId;
    }
}
