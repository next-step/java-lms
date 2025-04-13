package nextstep.courses.domain.session;

import nextstep.courses.CannotEnrollException;

import java.util.Objects;

public class SessionCapacity {
    private static final int MIN_CAPACITY = 1;

    private int capacity;
    private final int maxCapacity;

    public SessionCapacity(int capacity, int maxCapacity) {
        this.capacity = capacity;
        this.maxCapacity = maxCapacity;
        checkValidCapacity();
    }

    public SessionCapacity(int maxCapacity) {
        this(0, maxCapacity);
    }

    private void checkValidCapacity() {
        if (maxCapacity < MIN_CAPACITY) {
            throw new IllegalArgumentException();
        }
        isValidCapacity();
    }

    public void increase() {
        capacity++;
        isValidCapacity();
    }

    @Override
    public int hashCode() {
        return Objects.hash(capacity, maxCapacity);
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        SessionCapacity sessionCapacity = (SessionCapacity) object;
        return sessionCapacity.capacity == this.capacity && sessionCapacity.maxCapacity == this.maxCapacity;
    }

    private void isValidCapacity(){
        if(this.capacity > this.maxCapacity) {
            throw new CannotEnrollException("최대 수용 인원을 현재 인원이 초과할 수 없다.");
        }
    }
}
