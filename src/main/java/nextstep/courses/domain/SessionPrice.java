package nextstep.courses.domain;

import nextstep.courses.exception.SessionPriceException;
import nextstep.payments.domain.Payment;

public class SessionPrice {

    private final int price;

    public SessionPrice(int price) {
        this.price = price;
    }

    public void isSameBy(Payment payment) {
        if(!payment.isSameBy(price)){
            throw new SessionPriceException("결제 금액과 강의 금액이 일치 하지 않습니다.");
        }
    }
}
