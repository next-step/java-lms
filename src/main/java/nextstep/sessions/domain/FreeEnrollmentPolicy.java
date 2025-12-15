package nextstep.sessions.domain;

import nextstep.payments.domain.Payment;

public class FreeEnrollmentPolicy implements EnrollmentPolicy {

    static final String ERROR_FREE_COURSE_CAPACITY_MUST_BE_UNLIMITED = "무료 강의는 최대 수강 인원이 없어야 합니다";

    @Override
    public boolean canEnroll(Capacity capacity, Payment payment) {
        return true;
    }

    @Override
    public void validate(Capacity capacity) {
        if (!capacity.isUnlimited()) {
            throw new IllegalArgumentException(ERROR_FREE_COURSE_CAPACITY_MUST_BE_UNLIMITED);
        }
    }
}
