package nextstep.courses.domain;

public class Image {

    private final Long id;
    private final String path;
    private final Size size;
    private final ImageType imageType;

    public Image(Long id, String path, Size size, ImageType imageType) {
        this.id = id;
        this.path = path;
        this.size = size;
        this.imageType = imageType;
    }
    public Image(String path, Size size, ImageType imageType) {
        this(0L, path, size, imageType);
    }

    public Image(String path, int width, int height, ImageType imageType) {
        this(0L, path, new Size(width, height), imageType);
    }

    public String path() {
        return path;
    }

    public int size() {
        return size.size();
    }

    public int width() {
        return size.width();
    }

    public int height() {
        return size.height();
    }

    public String imageType() {
        return imageType.type();
    }

}
