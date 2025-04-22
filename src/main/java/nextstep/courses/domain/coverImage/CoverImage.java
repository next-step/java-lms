package nextstep.courses.domain.coverImage;

public class CoverImage {
    private final Size size;
    private final ImageType type;

    public CoverImage(Size size, ImageType type) {
        this.size = size;
        this.type = type;
    }

    public Size size() {
        return size;
    }
}
