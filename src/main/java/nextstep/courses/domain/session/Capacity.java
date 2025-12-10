package nextstep.courses.domain.session;

public class Capacity {
    private final static int UNLIMITED = -1;
    private final int capacity;

    public Capacity() {
        this(UNLIMITED);
    }

    public Capacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isUnlimited() {
        return capacity == UNLIMITED;
    }

    public boolean isGreaterThan(int value) {
        return capacity > value;
    }
}
