package nextstep.courses.domain;

import java.util.Objects;

public class EnrollmentCount {

    private final int count;

    public EnrollmentCount(int count) {
        validateNegative(count);
        this.count = count;
    }

    private void validateNegative(int count) {
        if (count < 0) {
            throw new IllegalArgumentException("수강인원은 0이상이어야 합니다.");
        }
    }

    public boolean hasRemainingCount() {
        return count != 0;
    }

    public EnrollmentCount decreaseCount() {
        return new EnrollmentCount(this.count - 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EnrollmentCount)) return false;
        EnrollmentCount that = (EnrollmentCount) o;
        return count == that.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count);
    }
}
