package nextstep.session.domain;

import nextstep.exception.CapacityException;

public class Capacity {

    public static final int NO_CAPACITY = 0;
    private final int maxCapacity;
    private int enrolled = 0;

    public Capacity(int maxCapacity) {
        validateMaxCapacity(maxCapacity);
        this.maxCapacity = maxCapacity;
    }

    private void validateMaxCapacity(int maxCapacity) {
        if (maxCapacity < NO_CAPACITY) {
            throw new CapacityException("수용 인원은 음수일 수 없습니다.");
        }
    }

    public void enroll(int enrolledNumber) {
        int afterEnrolled = this.enrolled + enrolledNumber;
        validateEnrolledNumber(afterEnrolled);

        this.enrolled = afterEnrolled;
    }

    private void validateEnrolledNumber(int afterEnrolled) {
        if (afterEnrolled > maxCapacity) {
            throw new CapacityException("수용 가능인원을 초과하여 신청하였습니다.");
        }
    }

    public int getEnrolled() {
        return this.enrolled;
    }

    public boolean isAvailable() {
        return maxCapacity > this.enrolled;
    }
}
