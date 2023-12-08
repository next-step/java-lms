package nextstep.courses.type;

import java.util.Objects;

/**
 * 파일의 용량을 나타냅니다.
 * 불변객체입니다.
 */
public class Capacity {
    private final int capacity;
    private final CapacityUnit unit;

    public Capacity(int capacity, CapacityUnit unit) {
        validateCapacity(capacity);

        this.capacity = capacity;
        this.unit = unit;
    }

    private static void validateCapacity(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("파일 용량이 0보다 작을 수는 없습니다.");
        }
    }

    public void throwIfCapacityIsLagerThan(Capacity capacity) {
        if (this.unit != capacity.unit) {
            throw new IllegalArgumentException("단위가 불일치하여 비교할 수 없습니다.");
        }

        if (this.capacity > capacity.capacity) {
            throw new IllegalArgumentException("파일 용량이 " + capacity + unit.toSymbol() + "보다 작습니다.");
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
        return capacity == capacity1.capacity && unit == capacity1.unit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(capacity, unit);
    }

    @Override
    public String toString() {
        return "Capacity{" +
                "capacity=" + capacity +
                ", unit=" + unit.toSymbol() +
                '}';
    }
}
