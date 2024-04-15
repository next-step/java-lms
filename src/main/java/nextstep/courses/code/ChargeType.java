package nextstep.courses.code;

public enum ChargeType {

    FREE,
    PAID;

    ChargeType() {
    }

    public void validateChargeType(Long price) {
        if (isFree() && price != 0L) {
            throw new IllegalArgumentException("무료 강의는 가격이 0이어야 합니다");
        }
        if (isPaid() && price == 0L) {
            throw new IllegalArgumentException("유료 강의는 가격이 0보다 커야 합니다");
        }
    }

    private boolean isFree() {
        return this.equals(FREE);
    }

    private boolean isPaid() {
        return this.equals(PAID);
    }
}
