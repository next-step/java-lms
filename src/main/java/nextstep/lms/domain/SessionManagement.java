package nextstep.lms.domain;

import nextstep.lms.dto.EnrollApplicationDTO;

public class SessionManagement {
    private final PricingPolicy pricingPolicy;
    private final int capacity;

    public SessionManagement(PricingPolicy pricingPolicy, int capacity) {
        this.pricingPolicy = pricingPolicy;
        this.capacity = capacity;
    }

    public void enroll(Students students, EnrollApplicationDTO enrollApplicationDTO) {
        if (pricingPolicy.canEnroll(enrollApplicationDTO.getTuitionFee())) {
            students.enroll(capacity, enrollApplicationDTO.getNsUser());
        }
    }
}
