package nextstep.courses.domain.session;

import java.util.Objects;
import java.util.OptionalLong;

import nextstep.courses.CannotEnrollException;
import nextstep.courses.domain.Amount;

/**
 * 최대 수강 인원 제한은 충분히 큰 Long타입의 MAX value로 한다.
 */
public class EnrollmentPolicy {
    private static final long UNLIMITED_CAPACITY = Long.MAX_VALUE;
    private final Amount price;
    private final long maxCapacity;

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

    // 남은 좌석 수 : 무료 강의면 OptionalLong.Empty()
    public OptionalLong remainingSeats(long enrolledCount) {
        if (isFree()) {
            return OptionalLong.empty();
        }
        return OptionalLong.of(Math.max(maxCapacity - enrolledCount, 0));
    }

    /* ------------ 정책 검증 ------------ */
    // 유료 강의의 경우 최대 수강인원보다 현재 수강인원이 적어야 함.
    public boolean hasCapacity(long enrolledCount) {
        return isFree() || enrolledCount < maxCapacity;
    }

    // 유료 강의의 경우 수강료와 결제금액이 같아야 함.
    public boolean matchesPayment(Amount paidAmount) {
        return isFree() || price.equals(paidAmount);
    }

    public void validateEnrollment(long enrolledCount) {
        if (!hasCapacity(enrolledCount)) {
            throw new CannotEnrollException("잔여 좌석이 없습니다");
        }
    }

    /* ------------ 정보성 메서드 ------------ */
    public boolean isFree() {
        return price.isZero();
    }

    public Amount price() {
        return price;
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
