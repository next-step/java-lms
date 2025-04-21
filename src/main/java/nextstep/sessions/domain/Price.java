package nextstep.sessions.domain;

public class Price {
    private final int value;

    Price(int value) {
        validate();
        this.value = value;
    }

    int getValue() {
        return value;
    }

    private void validate() {
        if (value < 0) {
            throw new IllegalArgumentException("가격은 0 이상이어야 합니다.");
        }
    }
}
