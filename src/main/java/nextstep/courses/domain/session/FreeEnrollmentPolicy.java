package nextstep.courses.domain.session;

import java.util.OptionalLong;

import nextstep.courses.domain.Amount;

/**
 * 무료 강의에 대한 수강 정책
 */
public class FreeEnrollmentPolicy implements EnrollmentPolicy {
    @Override
    public OptionalLong remainingSeats(long enrolledCount) {
        return OptionalLong.empty();
    }

    @Override
    public boolean hasCapacity(long enrolledCount) {
        return true;
    }

    @Override
    public boolean matchesPayment(Amount paidAmount) {
        return paidAmount.isZero();
    }

    @Override
    public void validateEnrollment(long enrolledCount) {

    }

    @Override
    public Amount price() {
        return Amount.of(0);
    }

    @Override
    public boolean isFree() {
        return true;
    }
}
