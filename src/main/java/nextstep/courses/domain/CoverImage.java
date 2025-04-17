package nextstep.courses.domain;

public class CoverImage {
    private final Size size;
    private final Width width;
    private final Height height;

    public CoverImage() {
        this(0L, 300, 200);
    }

    CoverImage(Long size) {
        this(size, 300, 200);
    }

    CoverImage(Integer width, Integer height) {
        this(0L, width, height);
    }

    public CoverImage(Long size, Integer width, Integer height) {
        this.size = new Size(size);
        this.width = new Width(width);
        this.height = new Height(height);
    }
}
