package nextstep.courses.domain;

import java.util.Objects;

public class UnlimitedCapacity implements Capacity {
    private final int current;

    public UnlimitedCapacity() {
        this(0);
    }

    public UnlimitedCapacity(int current) {
        this.current = current;
    }

    @Override
    public boolean hasRoom() {
        return true;
    }

    @Override
    public int remaining() {
        return Integer.MAX_VALUE;
    }

    @Override
    public Capacity increase() {
        return new UnlimitedCapacity(current + 1);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UnlimitedCapacity that = (UnlimitedCapacity) o;
        return current == that.current;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(current);
    }
}
