package nextstep.sessions.domain;

public class SessionPrice {

    private final long value;

    public SessionPrice(final long value) {
        validatePositive(value);
        this.value = value;
    }

    public boolean isNotFree() {
        return value != 0;
    }

    public void validatePriceEquality(final long price) {
        if (value != price) {
            throw new IllegalArgumentException("현재 가격은 " + price + "원 입니다. " + value + "원과 일치하지 않습니다.");
        }
    }

    private void validatePositive(final long value) {
        if (value < 0) {
            throw new IllegalArgumentException("입력한 가격은 " + value + "원 입니다. 가격은 0보다 작을 수 없습니다.");
        }
    }

    @Override
    public String toString() {
        return "Price{" +
                "value=" + value +
                '}';
    }
}
