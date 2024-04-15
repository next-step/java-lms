package nextstep.courses.domain;

import java.util.Objects;

public class Amount {

    private final int value;

    public Amount(int value) {
        this.value = value;
    }

    public boolean isNoLimit() {
        return this.value == 0;
    }

    public boolean isOver(Amount compare) {
        return this.value >= compare.value;
    }

    public boolean isSmaller(Amount count) {
        return this.value >= count.value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Amount)) return false;
        Amount amount = (Amount) o;
        return value == amount.value;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
