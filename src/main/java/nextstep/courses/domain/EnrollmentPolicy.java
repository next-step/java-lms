package nextstep.courses.domain;

import java.util.Objects;
import java.util.OptionalLong;

import nextstep.courses.CannotEnrollException;

/**
 * 최대 수강 인원 제한은 충분히 큰 Long타입의 MAX value로 한다.
 */
public class EnrollmentPolicy {
    private final Amount price;
    private final long maxCapacity;
    private static final long UNLIMITED_CAPACITY = Long.MAX_VALUE;

    private EnrollmentPolicy(Amount price, long maxCapacity) {
        this.price = price;
        this.maxCapacity = maxCapacity;
    }

    public static EnrollmentPolicy free() {
        return new EnrollmentPolicy(Amount.of(0), UNLIMITED_CAPACITY);
    }

    public static EnrollmentPolicy paid(int price, long maxCapacity) {
        if (price == 0) {
            throw new IllegalArgumentException("유료 강의의 가격은 0보다 커야 합니다");
        }
        return new EnrollmentPolicy(Amount.of(price), maxCapacity);
    }

    public boolean isFree() {
        return price.isZero();
    }

    //TODO 알아보기 힘들거같은데...
    public OptionalLong remainingSeats(long enrolledCount) {
        if (isFree()) {
            return OptionalLong.empty();
        }
        return OptionalLong.of(Math.max(maxCapacity - enrolledCount, 0));
    }

    // 추후 한명이 여러명 분의 수강 결제를 할 수도 있으니 parameter에 long타입 받아둠
    public boolean canEnroll(long enrollCount) {
        if (isFree()) {
            return true;
        }
        return maxCapacity - enrollCount > 0;
    }

    public void validateEnrollment(long enrollment) {
        if (!canEnroll(enrollment)) {
            throw new CannotEnrollException("잔여 좌석이 없습니다");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;
        EnrollmentPolicy that = (EnrollmentPolicy)o;
        return maxCapacity == that.maxCapacity && Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, maxCapacity);
    }
}
