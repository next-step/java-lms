package nextstep.courses.domain.session.coverimage.wrapper;

import nextstep.courses.exception.ImageFileInfoException;

public class CapacitySize {

    private static final long MAXIMUM = 1_048_576L;
    private static final long MINIMUM = 1L;
    private static final long MB_DIVIDER = 1024 * 1024L;

    private long bytes;

    public CapacitySize(long bytes) throws ImageFileInfoException {
        validateCapacity(bytes);
        this.bytes = bytes;
    }

    private static void validateCapacity(long bytes) throws ImageFileInfoException {
        if (bytes < MINIMUM) {
            throw new ImageFileInfoException(
                String.format("썸네일 이미지의 용량 크기는 %sbyte 이상 이어야 합니다. 현재 크기 :: %sbytes", MINIMUM, bytes)
            );
        }

        if (bytes > MAXIMUM) {
            throw new ImageFileInfoException(
                String.format("썸네일 이미지의 용량 크기는 %sMB 이하 이어야 합니다. 현재 크기 :: %sbytes", MAXIMUM/ MB_DIVIDER, bytes)
            );
        }
    }
}
