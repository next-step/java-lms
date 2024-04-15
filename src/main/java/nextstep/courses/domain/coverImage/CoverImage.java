package nextstep.courses.domain.coverImage;

import static java.util.Objects.nonNull;
import static nextstep.courses.ExceptionMessage.INVALID_COVER_IMAGE;

public class CoverImage {
    private static final double MAX_CAPACITY = 1024 * 1024;

    private Long id;
    private final String name;
    private final double capacity;
    private final Dimensions dimensions;
    private final ImageType imageType;

    public CoverImage(String name, double capacity, double width, double height, ImageType imageType) {
        this(null, name, capacity, new Dimensions(width, height), imageType);
    }

    public CoverImage(Long id, String name, double capacity, Dimensions dimensions, ImageType imageType) {
        validateCoverImageInput(capacity);
        this.id = id;
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

    public void updateAsSavedCoverImage(Long id) {
        if (nonNull(this.id)) {
            return;
        }
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getCapacity() {
        return capacity;
    }

    public double getWidth() {
        return dimensions.getWidth();
    }

    public double getHeight() {
        return dimensions.getHeight();
    }

    public ImageType getImageType() {
        return imageType;
    }
}
