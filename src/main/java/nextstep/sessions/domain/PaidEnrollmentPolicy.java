package nextstep.sessions.domain;

import nextstep.payments.domain.Payment;

public class PaidEnrollmentPolicy implements EnrollmentPolicy {

    static final String ERROR_PAID_SESSION_CAPACITY_REQUIRED = "유료 강의는 최대 수강 인원이 있어야 합니다";
    private final int fee;

    public PaidEnrollmentPolicy(int fee) {
        this.fee = fee;
    }

    @Override
    public boolean canEnroll(Capacity capacity, Payment payment) {
        return capacity.hasAvailableSeat() && payment != null && payment.isPaidFor(fee);
    }

    @Override
    public void validate(Capacity capacity) {
        if (capacity.isUnlimited()) {
            throw new IllegalArgumentException(ERROR_PAID_SESSION_CAPACITY_REQUIRED);
        }
    }
}
