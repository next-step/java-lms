package nextstep.courses.type;

import java.util.Objects;

/**
 * 파일의 용량을 나타냅니다.
 * 단위는 KB입니다.
 * 불변객체입니다.
 */
public class Capacity {
    private final int capacity;

    public Capacity(int capacity) {
        validateCapacity(capacity);

        this.capacity = capacity;
    }

    private static void validateCapacity(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("파일 용량이 0보다 작을 수는 없습니다.");
        }
    }

    public void throwIfCapacityIsLagerThan(Capacity capacity) {
        if (this.capacity > capacity.capacity) {
            throw new IllegalArgumentException("파일 용량이 " + capacity + "KB보다 작습니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Capacity capacity1 = (Capacity) o;
        return capacity == capacity1.capacity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(capacity);
    }

    @Override
    public String toString() {
        return "Capacity{" +
                "capacity=" + capacity + "KB" +
                '}';
    }

    public int toInt() {
        return this.capacity;
    }
}
