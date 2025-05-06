package nextstep.courses.domain.session;

import java.util.OptionalLong;

import nextstep.courses.CannotEnrollException;
import nextstep.courses.domain.Amount;

/**
 * 유료 강의에 대한 수강 정책
 */
public class PaidEnrollmentPolicy implements EnrollmentPolicy {
    private final Amount price;
    private final long maxCapacity;

    public PaidEnrollmentPolicy(Amount price, long maxCapacity) {
        if (price.isZero()) {
            throw new IllegalArgumentException("유료 강의의 가격은 0보다 커야 합니다.");
        }
        this.price = price;
        this.maxCapacity = maxCapacity;
    }

    @Override
    public OptionalLong remainingSeats(long enrolledCount) {
        long remain = Math.max(maxCapacity - enrolledCount, 0);
        return OptionalLong.of(remain);
    }

    @Override
    public boolean hasCapacity(long enrolledCount) {
        return enrolledCount < maxCapacity;
    }

    @Override
    public boolean matchesPayment(Amount paidAmount) {
        return price.equals(paidAmount);
    }

    @Override
    public void validateEnrollment(long enrolledCount) {
        if (!hasCapacity(enrolledCount)) {
            throw new CannotEnrollException("잔여 좌석이 없습니다.");
        }
    }

    @Override
    public Amount price() {
        return price;
    }

    @Override
    public boolean isFree() {
        return false;
    }
}
