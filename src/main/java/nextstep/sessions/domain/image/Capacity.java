package nextstep.sessions.domain.image;

import java.util.Objects;

public class Capacity {

    private static final int CAPACITY = 1024 * 1024;

    private final int capacity;

    public Capacity(int capacity) {
        validateCapacity(capacity);
        this.capacity = capacity;
    }

    private void validateCapacity(int capacity) {
        if (capacity > CAPACITY) {
            throw new IllegalArgumentException(String.format("이미지크기는 1MB(%s)를 초과할 수 없다", CAPACITY));
        }
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Capacity capacity1 = (Capacity) object;
        return capacity == capacity1.capacity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(capacity);
    }

    public int getCapacity() {
        return capacity;
    }
}
