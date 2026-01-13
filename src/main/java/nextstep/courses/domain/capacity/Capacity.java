package nextstep.courses.domain.capacity;

import java.util.Objects;

public class Capacity {
    private int capacity;

    public Capacity(String capacity) {
        this(Integer.parseInt(capacity));
    }

    public Capacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean exceed(Capacity other) {
        return capacity > other.capacity;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Capacity capacity1 = (Capacity) o;
        return capacity == capacity1.capacity;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(capacity);
    }
}
