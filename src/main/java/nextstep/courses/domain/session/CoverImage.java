package nextstep.courses.domain.session;

import static nextstep.courses.ExceptionMessage.INVALID_COVER_IMAGE;

public class CoverImage {
    private static final double MAX_CAPACITY = 1024 * 1024;

    private final String name;
    private final double capacity;
    private final Dimensions dimensions;
    private final ImageType imageType;

    public CoverImage(String name, double capacity, double width, double height, ImageType imageType) {
        this(name, capacity, new Dimensions(width, height), imageType);
    }

    public CoverImage(String name, double capacity, Dimensions dimensions, ImageType imageType) {
        validateCoverImageInput(capacity);
        this.name = name;
        this.capacity = capacity;
        this.dimensions = dimensions;
        this.imageType = imageType;
    }

    private void validateCoverImageInput(double capacity) {
        if (!isValidCapacity(capacity)) {
            throw new IllegalArgumentException(INVALID_COVER_IMAGE.message());
        }
    }

    private boolean isValidCapacity(double capacity) {
        return capacity <= MAX_CAPACITY;
    }
}
