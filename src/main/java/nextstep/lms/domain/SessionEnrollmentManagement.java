package nextstep.lms.domain;

import nextstep.lms.enums.PricingTypeEnum;
import nextstep.lms.enums.SessionStatusEnum;
import nextstep.payments.domain.Payment;

public class SessionEnrollmentManagement {
    private final PricingPolicy pricingPolicy;
    private final SessionStatusEnum sessionStatusEnum;
    private final int capacity;

    public SessionEnrollmentManagement(String pricingType, Long tuitionFee, String sessionStatus, int capacity) {
        this(new PricingPolicy(PricingTypeEnum.valueOf(pricingType), tuitionFee), SessionStatusEnum.valueOf(sessionStatus), capacity);
    }

    public SessionEnrollmentManagement(PricingPolicy pricingPolicy, SessionStatusEnum sessionStatusEnum, int capacity) {
        this.pricingPolicy = pricingPolicy;
        this.sessionStatusEnum = sessionStatusEnum;
        this.capacity = capacity;
    }

    public boolean enroll(Students students, Payment payment) {
        sessionStatusCheck();
        if (!pricingPolicy.canEnroll(payment)) {
            return false;
        }
        if (pricingPolicy.isFree()) {
            students.freeSessionEnroll(payment.getNsUserId());
            return true;
        }
        students.paidSessionEnroll(capacity, payment.getNsUserId());
        return true;
    }

    private void sessionStatusCheck() {
        if (!sessionStatusEnum.isRecruiting()) {
            throw new IllegalArgumentException("모집중이 아닙니다.");
        }
    }

    public String getPricingType() {
        return pricingPolicy.getPricingType();
    }

    public Long getTuitionFee() {
        return pricingPolicy.getTuitionFee();
    }

    public String getSessionStatus() {
        return sessionStatusEnum.name();
    }

    public int getCapacity() {
        return capacity;
    }

}
