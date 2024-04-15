package nextstep.courses.domain;

public enum ChargeStatus {
    FREE,
    PAID;

    public static final String PRICE_OF_FREE_SESSION_ERROR_MESSAGE = "무료 강의의 가격은 0원 입니다.";

    public void validate(long price) {
        if (isFree() && price > 0) {
            throw new IllegalArgumentException(PRICE_OF_FREE_SESSION_ERROR_MESSAGE);
        }
    }

    private boolean isFree() {
        return this == FREE;
    }
}
