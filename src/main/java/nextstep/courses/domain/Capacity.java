package nextstep.courses.domain;

import nextstep.courses.exception.BusinessInvalidValueException;

public class Capacity {
    private final int capacity;

    public Capacity(int capacity) {
        this.capacity = capacity;
    }

    public void validateCapacity(int count) {
        if (count >= capacity) {
            throw new BusinessInvalidValueException("최대수강인원을 초과했습니다.");
        }
    }

    public int capacity() {
         return capacity;
    }
}

