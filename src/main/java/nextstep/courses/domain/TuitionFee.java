package nextstep.courses.domain;

import java.util.Objects;

public class TuitionFee {
    private final int amount;

    public TuitionFee(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("수강료는 0 이거나 음수일 수 없습니다.");
        }
        this.amount = amount;
    }

    public boolean isSameAmount(int amount) {
        return this.amount == amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TuitionFee)) return false;
        TuitionFee that = (TuitionFee) o;
        return amount == that.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}