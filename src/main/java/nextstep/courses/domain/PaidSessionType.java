package nextstep.courses.domain;

import nextstep.payments.domain.Payment;

public class PaidSessionType implements SessionType {

    private int maximumHeadCount;
    private long price;

    public PaidSessionType(int maximumHeadCount, long price) {
        this.maximumHeadCount = maximumHeadCount;
        this.price = price;
    }

    private void canRegistered(long payedPrice, int headCount) {
        int nextHeadCount = headCount + 1;
        if (maximumHeadCount < nextHeadCount) {
            throw new IllegalArgumentException("인원수가 초과했습니다.");
        }
        if (payedPrice < this.price) {
            throw new IllegalArgumentException("지불하신 금액이 모자랍니다.");
        }
    }

    public PaidSessionType registered(long payedPrice, int headCount) {
        canRegistered(payedPrice, headCount);
        return new PaidSessionType(headCount + 1, this.price);
    }
}
