package nextstep.lms.domain;

import nextstep.lms.dto.EnrollApplicationDTO;
import nextstep.lms.enums.SessionStatusEnum;

public class SessionEnrollmentManagement {
    private final PricingPolicy pricingPolicy;
    private final SessionStatusEnum sessionStatusEnum;
    private final int capacity;

    public SessionEnrollmentManagement(PricingPolicy pricingPolicy, SessionStatusEnum sessionStatusEnum, int capacity) {
        this.pricingPolicy = pricingPolicy;
        this.sessionStatusEnum =sessionStatusEnum;
        this.capacity = capacity;
    }

    public void enroll(Students students, EnrollApplicationDTO enrollApplicationDTO) {
        if (pricingPolicy.canEnroll(enrollApplicationDTO.getTuitionFee())) {
            students.enroll(capacity, enrollApplicationDTO.getUserId());
        }
    }

    public void sessionStatusCheck() {
        if (!sessionStatusEnum.isRecruiting()) {
            throw new IllegalArgumentException("모집중이 아닙니다.");
        }
    }
}
