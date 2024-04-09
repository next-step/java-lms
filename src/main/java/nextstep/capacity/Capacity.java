package nextstep.capacity;

public class Capacity {
    private final int capacity;

    public Capacity() {
        this(0);
    }

    public Capacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isLessThan(int other) { // 미만
        return capacity < other;
    }

    public boolean isLessThanOrEqual(int other) { // 이하
        return capacity <= other;
    }
}
