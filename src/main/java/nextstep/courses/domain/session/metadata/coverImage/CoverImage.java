package nextstep.courses.domain.session.metadata.coverImage;

public class CoverImage {
    private final Size size;
    private final ImageType type;
    private final Dimensions dimensions;

    public CoverImage(Size size, ImageType type, Dimensions dimensions) {
        this.size = size;
        this.type = type;
        this.dimensions = dimensions;
    }

}
