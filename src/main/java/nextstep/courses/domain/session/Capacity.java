package nextstep.courses.domain.session;

import java.util.Objects;

public class Capacity {

    private int value;

    public Capacity(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public boolean matchSize(int enrollmentsSize) {
        return this.value <= enrollmentsSize;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Capacity capacity = (Capacity) o;
        return value == capacity.value;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
