package nextstep.courses.domain;

import java.util.Objects;

public class Amount {

    private final int value;

    public Amount(int value) {
        this.value = value;
    }

    public boolean exist() {
        return this.value != -1;
    }

    public boolean isBigger(Amount other) {
        return this.value > other.value;
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
