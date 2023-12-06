package nextstep.courses.domain;

import nextstep.courses.exception.PaidSessionException;

import java.util.Objects;

public class Amount {
    private static final int MIN_FEE = 0;
    private final long value;

    public Amount(long value) {
        validate(value);
        this.value = value;
    }

    private static void validate(long value) {
        if (value < MIN_FEE) {
            throw new PaidSessionException("가격는 음수일 수 없습니다");
        }
    }

    public boolean isNotSameByLong(Long amount) {
        return value != amount;
    }

    public long value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Amount fee = (Amount) o;
        return value == fee.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
