package nextstep.courses.domain.session.constraint;

import lombok.Getter;

import java.util.Objects;

@Getter
public class SessionFee {
    private final long value;

    public SessionFee(long fee) {
        if (fee < 1) {
            throw new IllegalArgumentException("강의 요금은 0원 이상이여야 합니다.");
        }
        this.value = fee;
    }

    public boolean isSame(long value) {
        return this.value == value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionFee that = (SessionFee) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
