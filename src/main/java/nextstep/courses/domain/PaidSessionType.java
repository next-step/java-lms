package nextstep.courses.domain;

import nextstep.payments.domain.Payment;

public class PaidSessionType implements SessionType {

    private int maximumHeadCount;
    private int price;

    public PaidSessionType(int maximumHeadCount, int price) {
        this.maximumHeadCount = maximumHeadCount;
        this.price = price;
    }

    public void canRegistered(int payedPrice){
        int nextHeadCount = maximumHeadCount + 1;
        if(maximumHeadCount < nextHeadCount){
            throw new IllegalArgumentException("인원수가 초과했습니다.");
        }
        if(this.price != payedPrice){
            throw new IllegalArgumentException("지불 금액이 다릅니다.");
        }
    }

    public PaidSessionType registered(int payedPrice) {
        canRegistered(payedPrice);
        return new PaidSessionType(this.maximumHeadCount + 1, this.price);
    }
}
