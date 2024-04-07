package nextstep.courses.domain;

import nextstep.payments.domain.Payment;

public class SessionFee {
    private final Long fee;

    public SessionFee(Long fee) {
        if(fee < 0L){
            throw new IllegalArgumentException("수강료는 0이상이어야 합니다.");
        }
        this.fee = fee;
    }

    public boolean canPurchase(Payment payment) {
        return payment.canPay(this.fee);
    }

    public boolean isFree(){
        return fee == 0L;
    }
}
