package nextstep.courses.domain.coverimage;

import nextstep.courses.MaxImageSizeExceededException;

public class CoverImage {
    private static final int MAX_SIZE = 1024;
    private static final String ERR_MAX_SIZE_EXCEEDED = "커버 이미지는 1MB 이하여야 합니다.";

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
    }

    private void validateSize() throws MaxImageSizeExceededException {
        if (this.size > MAX_SIZE) {
            throw new MaxImageSizeExceededException(ERR_MAX_SIZE_EXCEEDED);
        }
    }
}
