package nextstep.courses.domain;

import static nextstep.courses.ExceptionMessage.INVALID_COVER_IMAGE;
import static nextstep.courses.domain.ImageType.*;

public class CoverImage {
    private static final double MAX_CAPACITY = 1024 * 1024;
    private static final double MIN_WIDTH = 300;
    private static final double MIN_HEIGHT = 200;
    private static final double RATIO_OF_WIDTH_HEIGHT = 3.0 / 2.0;

    private final String name;
    private final double capacity;
    private final double width;
    private final double height;
    private final ImageType imageType;

    private CoverImage(String name, double capacity, double width, double height, ImageType imageType) {
        validateCoverImageInput(capacity, width, height);

        this.name = name;
        this.capacity = capacity;
        this.width = width;
        this.height = height;
        this.imageType = imageType;
    }

    private void validateCoverImageInput(double capacity, double width, double height) {
        if (!isValidCapacity(capacity) || !isValidWidthHeight(width, height)) {
            throw new IllegalArgumentException(INVALID_COVER_IMAGE.message());
        }
    }

    private boolean isValidCapacity(double capacity) {
        return capacity <= MAX_CAPACITY;
    }

    private boolean isValidWidthHeight(double width, double height) {
        return width >= MIN_WIDTH && height >= MIN_HEIGHT && (width / height == RATIO_OF_WIDTH_HEIGHT);
    }

    public static CoverImage gifImage(String name, double capacity, double width, double height) {
        return new CoverImage(name, capacity, width, height, GIF);
    }

    public static CoverImage jpgImage(String name, double capacity, double width, double height) {
        return new CoverImage(name, capacity, width, height, JPG);
    }

    public static CoverImage jpegImage(String name, double capacity, double width, double height) {
        return new CoverImage(name, capacity, width, height, JPEG);
    }

    public static CoverImage pngImage(String name, double capacity, double width, double height) {
        return new CoverImage(name, capacity, width, height, PNG);
    }

    public static CoverImage svgImage(String name, double capacity, double width, double height) {
        return new CoverImage(name, capacity, width, height, SVG);
    }
}
