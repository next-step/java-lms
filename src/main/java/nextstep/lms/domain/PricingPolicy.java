package nextstep.lms.domain;

import nextstep.lms.enums.PricingTypeEnum;

public class PricingPolicy {
    private final PricingTypeEnum pricingTypeEnum;
    private final int tuitionFee;

    public PricingPolicy(PricingTypeEnum pricingTypeEnum, int tuitionFee) {
        this.pricingTypeEnum = pricingTypeEnum;
        this.tuitionFee = tuitionFee;
    }

    public boolean canEnroll(int tuitionFee) {
        if (pricingTypeEnum.isFree()) {
            return true;
        }
        if (this.tuitionFee != tuitionFee) {
            throw new IllegalArgumentException("결제금액이 수강료와 다릅니다.");
        }
        return true;
    }
}
