package nextstep.courses.domain.session.type;

import java.util.Objects;

public class PaidType implements SessionType {
    private final int maxCapacity;
    private final long tuitionFee;
    private final int studentCount;

    public PaidType(int maxCapacity, long tuitionFee) {
        this(maxCapacity, tuitionFee, 0);
    }

    public PaidType(int maxCapacity, long tuitionFee, int studentCount) {
        validateCapacity(maxCapacity, studentCount);
        this.maxCapacity = maxCapacity;
        this.tuitionFee = tuitionFee;
        this.studentCount = studentCount;
    }

    @Override
    public SessionType enroll(long payAmount) {
        int newCount = studentCount + 1;
        validateCapacity(maxCapacity, newCount);
        validateTuitionFee(payAmount);
        return new PaidType(maxCapacity, tuitionFee, newCount);
    }

    private void validateCapacity(int maxCapacity, int studentCount) {
        if (maxCapacity < studentCount) {
            throw new IllegalArgumentException("최대 수강 인원을 초과할 수 없습니다.");
        }
    }

    private void validateTuitionFee(long payAmount) {
        if (payAmount != tuitionFee) {
            throw new IllegalArgumentException("수강료와 지불한 금액이 정확히 일치해야 합니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaidType that = (PaidType) o;
        return maxCapacity == that.maxCapacity
            && tuitionFee == that.tuitionFee
            && studentCount == that.studentCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(maxCapacity, tuitionFee, studentCount);
    }
}