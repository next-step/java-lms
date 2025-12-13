package nextstep.sessions.domain;

public class Capacity {

    private static final String ERROR_MAX_CAPACITY_REQUIRED = "유료 강의는 최대 수강인원이 있어야 합니다";
    private static final String ERROR_ENROLL_COUNT_NEGATIVE = "수강 인원은 0 이상이어야 합니다";
    private static final String ERROR_ENROLL_COUNT_EXCEED = "수강 인원은 수강 정원을 초과할 수 없습니다";
    private static final String ERROR_CANNOT_ENROLL = "수강 신청을 할 수 없습니다";

    private static final int DEFAULT_ENROLL_COUNT = 0;

    private final Integer maxCapacity;
    private final int enrollCount;

    public Capacity(Integer maxCapacity) {
        this(maxCapacity, DEFAULT_ENROLL_COUNT);
    }

    Capacity(Integer maxCapacity, int enrollCount) {
        validateMaxCapacity(maxCapacity);
        validateEnrollCount(enrollCount);
        validateEnrollCountWithinCapacity(maxCapacity, enrollCount);

        this.maxCapacity = maxCapacity;
        this.enrollCount = enrollCount;
    }

    public Integer maxCapacity() {
        return maxCapacity;
    }

    public int enrollCount() {
        return enrollCount;
    }

    public boolean canEnroll() {
        return maxCapacity == null || enrollCount < maxCapacity;
    }

    public boolean isUnlimited() {
        return maxCapacity == null;
    }

    public Capacity increaseEnrollCount() {
        if (!canEnroll()) {
            throw new IllegalArgumentException(ERROR_CANNOT_ENROLL);
        }
        return new Capacity(maxCapacity, enrollCount + 1);
    }

    private static void validateMaxCapacity(Integer maxCapacity) {
        if (maxCapacity != null && maxCapacity <= 0) {
            throw new IllegalArgumentException(ERROR_MAX_CAPACITY_REQUIRED);
        }
    }

    private static void validateEnrollCount(int enrollCount) {
        if (enrollCount < 0) {
            throw new IllegalArgumentException(ERROR_ENROLL_COUNT_NEGATIVE);
        }
    }

    private static void validateEnrollCountWithinCapacity(Integer maxCapacity, int enrollCount) {
        if (maxCapacity != null && enrollCount > maxCapacity) {
            throw new IllegalArgumentException(ERROR_ENROLL_COUNT_EXCEED);
        }
    }
}
