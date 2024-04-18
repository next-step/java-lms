package nextstep.courses.domain.session;

import java.util.Objects;

public class EnrollmentCount {

    static final int MINIMUM_ENROLLMENT_COUNT = 0;

    private final int value;

    public EnrollmentCount(final int value) {
        validateEnrollmentCountIsNotNegative(value);

        this.value = value;
    }

    private void validateEnrollmentCountIsNotNegative(final int value) {
        if (value < MINIMUM_ENROLLMENT_COUNT) {
            throw new IllegalArgumentException("수강 신청 인원 수는 0명 미만일 수 없습니다. 인원 수: " + value);
        }
    }

    public int value() {
        return this.value;
    }

    public boolean isLessThan(final EnrollmentCount otherEnrollmentCount) {
        return this.value < otherEnrollmentCount.value;
    }

    public EnrollmentCount increase() {
        return new EnrollmentCount(this.value + 1);
    }

    @Override
    public boolean equals(final Object otherEnrollmentCount) {
        if (this == otherEnrollmentCount) {
            return true;
        }

        if (otherEnrollmentCount == null || getClass() != otherEnrollmentCount.getClass()) {
            return false;
        }

        return this.value == ((EnrollmentCount)otherEnrollmentCount).value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.value);
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }
}