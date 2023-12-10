package nextstep.courses.domain;

public enum ChargeStatus {

    FREE,
    CHARGE;

    public void valid(int price) {
        if (isFree() && price > 0) {
            throw new IllegalArgumentException("무료 강의의 가격은 0입니다");
        }
    }

    private boolean isFree() {
        return this == FREE;
    }
}
