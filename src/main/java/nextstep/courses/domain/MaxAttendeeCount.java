package nextstep.courses.domain;

public class MaxAttendeeCount {
    private final Integer maxAttendeeCount;

    public MaxAttendeeCount(Integer maxAttendeeCount) {
        if (maxAttendeeCount <= 0) {
            throw new IllegalArgumentException("최대 수강생 수는 0보다 커야 합니다.");
        }
        this.maxAttendeeCount = maxAttendeeCount;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        MaxAttendeeCount that = (MaxAttendeeCount) o;
        return maxAttendeeCount.equals(that.maxAttendeeCount);
    }

    @Override
    public int hashCode() {
        return maxAttendeeCount.hashCode();
    }
}
