package nextstep.courses.domain.session.metadata.coverImage;

public class CoverImage {
    private final Long id;
    private final Size size;
    private final ImageType type;
    private final Dimensions dimensions;

    public CoverImage(Long id, Size size, ImageType type, Dimensions dimensions) {
        this.id = id;
        this.size = size;
        this.type = type;
        this.dimensions = dimensions;
    }

    /* getter */

    public Long getId() {
        return id;
    }

    public Long getSize() {
        return size.getBytes();
    }

    public int getWidth() {
        return dimensions.getWidth();
    }

    public int getHeight() {
        return dimensions.getHeight();
    }

    public String getExtension() {
        return type.getExtension();
    }

}
