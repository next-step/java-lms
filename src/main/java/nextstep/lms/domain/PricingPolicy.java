package nextstep.lms.domain;

import nextstep.lms.enums.PricingTypeEnum;
import nextstep.payments.domain.Payment;

public class PricingPolicy {
    private final PricingTypeEnum pricingTypeEnum;
    private final Long tuitionFee;

    public PricingPolicy(PricingTypeEnum pricingTypeEnum, Long tuitionFee) {
        this.pricingTypeEnum = pricingTypeEnum;
        this.tuitionFee = tuitionFee;
    }

    public void canEnrollCheck(Payment payment) {
        if (pricingTypeEnum.isPaid() && payment.isDifferentAmount(this.tuitionFee)) {
            throw new IllegalArgumentException("결제금액이 수강료와 다릅니다.");
        }
    }

    public boolean isPaid() {
        return pricingTypeEnum.isPaid();
    }

    public String getPricingType() {
        return pricingTypeEnum.name();
    }

    public Long getTuitionFee() {
        return tuitionFee;
    }
}
