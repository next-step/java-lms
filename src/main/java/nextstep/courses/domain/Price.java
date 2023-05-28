package nextstep.courses.domain;

public class Price {
    private final long value;

    public static final Price FREE = new Price(0L);

    public Price(long value) {
        this.value = value;
    }

    private long getValue() {
        return this.value;
    }

    public boolean isFree() {
        return this.value == 0L;
    }

    public boolean isNotFree() {
        return !isFree();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Price) {
            return this.value == ((Price) obj).getValue();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(this.value);
    }
}
