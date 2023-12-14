package nextstep.courses.domain.coverimage;

import nextstep.courses.InvalidImageDimensionException;
import nextstep.courses.MaxImageSizeExceededException;
import nextstep.courses.UnsupportedImageTypeException;

import java.util.Arrays;
import java.util.List;

public class CoverImage {
    private static final int MAX_SIZE = 1024;
    private static final int MIN_WIDTH = 300;
    private static final int MIN_HEIGHT = 200;
    private static final double VALID_DIMENSION_RATIO = (double) 3/2; // ratio = width/height
    private static final String ERR_MAX_SIZE_EXCEEDED = "커버 이미지는 1MB 이하여야 합니다.";
    private static final String ERR_TOO_SMALL_IMAGE = String.format("커버 이미지의 최소 width는 %d, 최소 height는 %d입니다.", MIN_WIDTH, MIN_HEIGHT);
    private static final String ERR_INVALID_RATIO = String.format("커버 이미지의 height 대비 width의 비율은 %f여야 합니다.", VALID_DIMENSION_RATIO);

    private int size; // KB 단위, 1MB = 1024KB
    private ImageType type;
    private int width;
    private int height;

    public CoverImage() {

    }

    public CoverImage(int size, String type, int width, int height) throws Exception {
        this.size = size;
        this.type = ImageType.of(type);
        this.width = width;
        this.height = height;
        this.validate();
    }

    private void validate() throws Exception {
        validateSize();
        validateDimension();
    }

    private void validateSize() throws MaxImageSizeExceededException {
        if (this.size > MAX_SIZE) {
            throw new MaxImageSizeExceededException(ERR_MAX_SIZE_EXCEEDED);
        }
    }

    private void validateDimension() throws InvalidImageDimensionException {
        if (this.width < MIN_WIDTH || this.height < MIN_HEIGHT) {
            throw new InvalidImageDimensionException(ERR_TOO_SMALL_IMAGE);
        }

        if (Double.compare((double) this.width/this.height, VALID_DIMENSION_RATIO) != 0) {
            throw new InvalidImageDimensionException(ERR_INVALID_RATIO);
        }
    }
}
