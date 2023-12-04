package nextstep.courses.domain.session;

import nextstep.courses.exception.NotMatchAmountException;

import java.text.DecimalFormat;

public class Amount {

    private static final DecimalFormat formatter = new DecimalFormat("###,###");
    private Long amount;

    public Amount(Long amount) {
        this.amount = amount;
    }

    public void validate(Long amount) throws NotMatchAmountException {
        if (this.amount.equals(amount)) {
            return;
        }

        throw new NotMatchAmountException(String.format("결제 금액이 강의 금액과 일치하지 않습니다. 결제 금액 :: %s원", formatter.format(amount)));
    }
}
