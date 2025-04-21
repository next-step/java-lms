package nextstep.courses.domain.session.constraint;

import lombok.Getter;

import java.util.Objects;

@Getter
public class SessionConstraint {

    private final SessionFee fee;

    private final SessionCapacity capacity;

    public SessionConstraint(long fee, int capacity) {
        this(new SessionFee(fee), new SessionCapacity(capacity));
    }

    public SessionConstraint(SessionFee fee, SessionCapacity capacity) {
        this.fee = fee;
        this.capacity = capacity;
    }

    public boolean isSameFee(long amount) {
        return fee.isSame(amount);
    }

    public boolean isGreaterThenCapacity(int value) {
        return capacity.isGreaterThan(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionConstraint that = (SessionConstraint) o;
        return Objects.equals(fee, that.fee) && Objects.equals(capacity, that.capacity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fee, capacity);
    }
}
