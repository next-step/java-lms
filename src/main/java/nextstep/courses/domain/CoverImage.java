package nextstep.courses.domain;

import java.time.LocalDateTime;

public class CoverImage extends BaseTimeEntity{

    private final ImageCapacity capacity;
    private final ImageType type;
    private final ImageDimension dimension;

    public CoverImage(ImageCapacity capacity, ImageType type, ImageDimension dimension) {
        super(LocalDateTime.now(), LocalDateTime.now());
        this.capacity = capacity;
        this.type = type;
        this.dimension = dimension;
    }

    public ImageCapacity getCapacity() {
        return capacity;
    }

    public ImageType getType() {
        return type;
    }

    public ImageDimension getDimension() {
        return dimension;
    }
}
