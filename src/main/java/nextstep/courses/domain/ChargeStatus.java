package nextstep.courses.domain;

public enum ChargeStatus {
    FREE, CHARGE;

    public void valid(int price) {
        if (price < 0) {
            throw new IllegalArgumentException("가격은 음수일 수 없습니다.");
        }
        if (this.equals(ChargeStatus.FREE)) {
            if (price!=0) {
                throw new IllegalArgumentException("무료 강의의 가격은 무조건 0원이어야 합니다.");
            }
        }
    }


}
