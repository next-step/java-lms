package nextstep.lms.domain;

import nextstep.lms.enums.PricingTypeEnum;
import nextstep.lms.enums.SessionRecruitmentEnum;
import nextstep.payments.domain.Payment;

public class SessionEnrollmentManagement {
    private final PricingPolicy pricingPolicy;
    private final SessionRecruitmentEnum sessionRecruitmentEnum;
    private final int capacity;

    public SessionEnrollmentManagement(PricingTypeEnum pricingTypeEnum, Long tuitionFee, SessionRecruitmentEnum sessionRecruitmentEnum, int capacity) {
        this(new PricingPolicy(pricingTypeEnum, tuitionFee), sessionRecruitmentEnum, capacity);
    }

    public SessionEnrollmentManagement(PricingPolicy pricingPolicy, SessionRecruitmentEnum sessionRecruitmentEnum, int capacity) {
        this.pricingPolicy = pricingPolicy;

        this.sessionRecruitmentEnum = sessionRecruitmentEnum;
        this.capacity = capacity;
    }

    public void enrollableCheck(Students students, Payment payment) {
        sessionRecruitmentCheck();
        pricingPolicy.canEnrollCheck(payment);

        if (pricingPolicy.isPaid()) {
            students.capacityCheck(capacity);
        }
    }

    private void sessionRecruitmentCheck() {
        if (!sessionRecruitmentEnum.isRecruiting()) {
            throw new IllegalArgumentException("모집중이 아닙니다.");
        }
    }

    public String getPricingType() {
        return pricingPolicy.getPricingType();
    }

    public Long getTuitionFee() {
        return pricingPolicy.getTuitionFee();
    }

    public String getSessionRecruitment() {
        return sessionRecruitmentEnum.name();
    }

    public int getCapacity() {
        return capacity;
    }

}
