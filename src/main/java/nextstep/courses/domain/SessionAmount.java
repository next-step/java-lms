package nextstep.courses.domain;

import nextstep.courses.InvalidSessionAmountException;

import java.util.Objects;

public class SessionAmount {

    private Long amount;

    public SessionAmount() {
    }

    public SessionAmount(Long amount) {
        validate(amount);

        this.amount = amount;
    }

    public boolean isCorrectAmount(Long amount) {
        return Objects.equals(this.amount, amount);
    }

    private void validate(Long amount) {
        if (amount == null) {
            throw new InvalidSessionAmountException("금액은 비어있을 수 없습니다.");
        }

        if (isNegative(amount)) {
            throw new InvalidSessionAmountException("금액은 음수일 수 없습니다.");
        }
    }

    private boolean isNegative(Long amount) {
        return amount < 0;
    }

}
