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

    public boolean canEnroll(Payment payment) {
        if (pricingTypeEnum.isFree()) {
            return true;
        }
        if (payment.isDifferentAmount(this.tuitionFee)) {
            throw new IllegalArgumentException("결제금액이 수강료와 다릅니다.");
        }
        return true;
    }

    public boolean isFree() {
        return pricingTypeEnum.isFree();
    }

    public String getPricingType() {
        return pricingTypeEnum.name();
    }

    public Long getTuitionFee() {
        return tuitionFee;
    }
}
