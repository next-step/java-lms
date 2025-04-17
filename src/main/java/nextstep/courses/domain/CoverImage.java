package nextstep.courses.domain;

public class CoverImage {
    private final Size size;
    private final Width width;
    private final Height height;
    private final ImageType type;

    public static CoverImage defaultImage() {
        return new CoverImage(0L, 300, 200, ImageType.JPEG);
    }

    public CoverImage(Long size, Integer width, Integer height, ImageType type) {
        this.size = new Size(size);
        this.width = new Width(width);
        this.height = new Height(height);
        this.type = type;
    }
}
