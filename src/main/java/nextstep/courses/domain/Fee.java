package nextstep.courses.domain;

public class Fee {

    int amount;

    public Fee(int amount, SessionType type) {
        validateFee(amount, type);
        this.amount = amount;
    }

    private void validateFee(int amount, SessionType type) {
        if (type == SessionType.FREE && amount > 0) {
            throw new IllegalArgumentException("무료 강의는 강의 금액이 있을 수 없습니다.");
        }

        if (type == SessionType.PAID && amount <= 0) {
            throw new IllegalArgumentException("유료 강의는 강의 금액이 0원 이상입니다.");
        }
    }

    public int getAmount() {
        return amount;
    }
}
