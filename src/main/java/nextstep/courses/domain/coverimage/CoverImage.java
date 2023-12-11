package nextstep.courses.domain.coverimage;

import nextstep.courses.MaxImageSizeExceededException;
import nextstep.courses.UnsupportedImageTypeException;

import java.util.Arrays;
import java.util.List;

public class CoverImage {
    private static final int MAX_SIZE = 1024;
    private static final List<String> ALLOWED_IMAGE_TYPES = Arrays.asList("gif", "jpg", "jpeg", "png", "svg");
    private static final String ERR_MAX_SIZE_EXCEEDED = "커버 이미지는 1MB 이하여야 합니다.";
    private static final String ERR_UNSUPPORTED_TYPE = "%s 타입의 이미지는 허용되지 않습니다. 커버 이미지는 아래의 타입들만 허용됩니다: %s";

    private int size; // KB 단위, 1MB = 1024KB
    private String type;
    private int width;
    private int height;

    public CoverImage(int size, String type, int width, int height) throws Exception {
        this.size = size;
        this.type = type;
        this.width = width;
        this.height = height;
        this.validate();
    }

    private void validate() throws Exception {
        validateSize();
        validateType();
    }

    private void validateSize() throws MaxImageSizeExceededException {
        if (this.size > MAX_SIZE) {
            throw new MaxImageSizeExceededException(ERR_MAX_SIZE_EXCEEDED);
        }
    }

    private void validateType() throws UnsupportedImageTypeException {
        if (!ALLOWED_IMAGE_TYPES.contains(this.type)) {
            throw new UnsupportedImageTypeException(String.format(ERR_UNSUPPORTED_TYPE, this.type, ALLOWED_IMAGE_TYPES.toString()));
        }
    }
}
