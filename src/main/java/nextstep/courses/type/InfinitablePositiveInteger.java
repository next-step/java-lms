package nextstep.courses.type;

import java.util.Objects;

/**
 * 무한대로 값을 설정할 수 있는 0 이상의 int형입니다.
 * 이 클래스는 값이 무한대로 설정되었을 때의 값 비교 처리를 위해 도입되었습니다
 * 불변 객체입니다.
 */
public class InfinitablePositiveInteger {
    private final int value;
    private final boolean isInfinite;

    private InfinitablePositiveInteger(int value, boolean isInfinite) {
        if (value < 0) {
            throw new IllegalArgumentException("0 미만의 값은 불가능합니다.");
        }

        this.value = value;
        this.isInfinite = isInfinite;
    }

    public static InfinitablePositiveInteger of(int value) {
        return new InfinitablePositiveInteger(value, false);
    }

    public static InfinitablePositiveInteger infinite() {
        return new InfinitablePositiveInteger(0, true);
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
        InfinitablePositiveInteger that = (InfinitablePositiveInteger) o;

        if (this.isInfinite != that.isInfinite) {
            return false;
        }

        if (this.isInfinite == true) {
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
