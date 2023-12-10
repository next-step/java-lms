package nextstep.lms.domain;

import nextstep.lms.enums.PricingTypeEnum;
import nextstep.lms.enums.SessionRecruitmentEnum;
import nextstep.payments.domain.Payment;

public class SessionEnrollmentManagement {
    private final PricingPolicy pricingPolicy;
    private final SessionRecruitmentEnum sessionRecruitmentEnum;
    private final int capacity;

    public SessionEnrollmentManagement(String pricingType, Long tuitionFee, String sessionRecruitment, int capacity) {
        this(new PricingPolicy(PricingTypeEnum.valueOf(pricingType), tuitionFee), SessionRecruitmentEnum.valueOf(sessionRecruitment), capacity);
    }

    public SessionEnrollmentManagement(PricingPolicy pricingPolicy, SessionRecruitmentEnum sessionRecruitmentEnum, int capacity) {
        this.pricingPolicy = pricingPolicy;

        this.sessionRecruitmentEnum = sessionRecruitmentEnum;
        this.capacity = capacity;
    }

    public Long enroll(Students students, Payment payment) {
        sessionRecruitmentCheck();
        pricingPolicy.canEnrollCheck(payment);

        if (pricingPolicy.isPaid()) {
            students.capacityCheck(capacity);
        }

        return students.enroll(payment.getNsUserId());
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
