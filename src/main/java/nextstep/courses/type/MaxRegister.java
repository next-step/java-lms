package nextstep.courses.type;

import java.util.Objects;

/**
 * 수강 가능 최대 인원을 나타내는 클래스로 int형을 warping합니다.
 * 이 클래스는 값이 무한대로 설정되었을 때의 값 비교 처리를 위해 도입되었습니다
 * 불변 객체입니다.
 */
public class MaxRegister {
    private final int value;
    private final boolean isInfinite;

    private MaxRegister(int value, boolean isInfinite) {
        if (value < 0) {
            throw new IllegalArgumentException("0 미만의 값은 불가능합니다.");
        }

        this.value = value;
        this.isInfinite = isInfinite;
    }

    public static MaxRegister of(int value) {
        return new MaxRegister(value, false);
    }

    public static MaxRegister infinite() {
        return new MaxRegister(0, true);
    }

    public boolean isLessThan(int value) {
        if (this.isInfinite) {
            return false;
        }

        return this.value < value;
    }

    public boolean isLargerThan(int value) {
        if (this.isInfinite) {
            return true;
        }

        return this.value > value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MaxRegister that = (MaxRegister) o;

        if (this.isInfinite != that.isInfinite) {
            return false;
        }

        if (this.isInfinite) {
            return true;
        }

        return this.value == that.value;
    }

    @Override
    public int hashCode() {
        if (this.isInfinite) {
            return Objects.hash(isInfinite);
        }

        return Objects.hash(value);
    }
}
