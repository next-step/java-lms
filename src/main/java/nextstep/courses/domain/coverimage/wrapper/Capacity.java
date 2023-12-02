package nextstep.courses.domain.coverimage.wrapper;

import nextstep.courses.exception.MaxUploadSizeExceededException;

public class Capacity {

    private static final long MAXIMUM = 1_048_576L;
    private static final long MB_DIVIDER = 1024 * 1024L;

    private long bytes;

    public Capacity(long bytes) throws MaxUploadSizeExceededException {
        if (bytes > MAXIMUM) {
            throw new MaxUploadSizeExceededException(String.format("썸네일 이미지의 용량은 %sMB이하 이어야 합니다.", MAXIMUM/ MB_DIVIDER));
        }

        this.bytes = bytes;
    }
}
