package nextstep.courses.domain;

import java.util.Objects;

public class Capacity {
    private final int value;

    public Capacity(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("수강 정원은 0이거나 음수일 수 없습니다.");
        }
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public boolean isFull(int registeredCount) {
        return registeredCount >= value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Capacity)) return false;
        Capacity capacity = (Capacity) o;
        return value == capacity.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}