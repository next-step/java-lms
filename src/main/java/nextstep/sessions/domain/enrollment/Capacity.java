package nextstep.sessions.domain.enrollment;

public class Capacity {

    private static final String ERROR_MAX_CAPACITY_REQUIRED = "유료 강의는 최대 수강인원이 있어야 합니다";

    private final Integer maxCapacity;
    private final boolean unlimited;

    Capacity(Integer maxCapacity, boolean unlimited) {
        validateMaxCapacity(maxCapacity);
        this.maxCapacity = maxCapacity;
        this.unlimited = unlimited;
    }

    public static Capacity limited(int maxCapacity) {
        return new Capacity(maxCapacity, false);
    }

    public static Capacity unlimited() {
        return new Capacity(Integer.MAX_VALUE, true);
    }

    public Integer maxCapacity() {
        return maxCapacity;
    }

    public boolean canEnroll(int enrollmentsSize) {
        return unlimited || enrollmentsSize < maxCapacity;
    }

    public boolean isUnlimited() {
        return unlimited;
    }

    public boolean isFull(int enrollmentsSize) {
        return enrollmentsSize >= maxCapacity;
    }

    private void validateMaxCapacity(Integer maxCapacity) {
        if (!unlimited && (maxCapacity == null || maxCapacity <= 0)) {
            throw new IllegalArgumentException(ERROR_MAX_CAPACITY_REQUIRED);
        }
    }

}
