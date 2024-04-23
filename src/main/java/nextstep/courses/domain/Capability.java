package nextstep.courses.domain;

import java.util.Objects;

public class Capability {

    private final int value;

    public Capability(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("강의 최대 수강 인원은 0명 이상이여야 합니다.");
        }
        this.value = value;
    }

    public boolean hasLimit() {
        return this.value != 0;
    }

    public boolean isBigger(Capability other) {
        return this.value > other.value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Capability)) return false;
        Capability capability = (Capability) o;
        return value == capability.value;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
