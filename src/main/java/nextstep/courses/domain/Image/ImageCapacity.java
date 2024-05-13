package nextstep.courses.domain.Image;

import nextstep.courses.OutOfImageCapacityException;

public class ImageCapacity {
    private final int LIMIT_CAPACITY = 1024;

    private final int capacity;

    /**
     * kilobyte 기준으로 작성하였습니다.
     * @param capacity
     */
    public ImageCapacity(int capacity) throws OutOfImageCapacityException {
        validCapacity(capacity);
        this.capacity = capacity;
    }

    private void validCapacity(int capacity) throws OutOfImageCapacityException {
        if (capacity > LIMIT_CAPACITY) {
            throw new OutOfImageCapacityException("이미지 용량은 1MB 이하여야 합니다.");
        }
    }
}
