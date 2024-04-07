package nextstep.qna.domain;

public class CoverImage {

    private final ImageCapacity capacity;
    private final ImageType type;
    private final ImageDimension dimension;

    public CoverImage(ImageCapacity capacity, ImageType type, ImageDimension dimension) {
        this.capacity = capacity;
        this.type = type;
        this.dimension = dimension;
    }
}
