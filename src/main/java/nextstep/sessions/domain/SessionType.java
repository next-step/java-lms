package nextstep.sessions.domain;

import nextstep.payments.domain.Money;
import nextstep.payments.domain.Payment;
import org.springframework.util.Assert;

public abstract class SessionType {
    private final Money price;

    public SessionType(Money price) {
        Assert.notNull(price, "강의 가격은 필수 데이터 입니다.");

        this.price = price;
    }

    /**
     * 강의의 최대 수강 인원을 초과하였는지 반환한다.
     * @param userCount 수강 인원
     * @return 최대 수강 이원 초과 여부
     */
    abstract boolean isFull(int userCount);

    /**
     * 결제 정보와 강의 금액이 일치하는지 확인한다.
     * @param payment 결제 정보
     * @return 결제 금액과 강의 금액 일치 여부
     */
    boolean equalsPrice(Payment payment) {
        return price.equals(payment.amount());
    }

}
