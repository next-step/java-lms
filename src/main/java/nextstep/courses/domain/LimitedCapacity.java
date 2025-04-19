package nextstep.courses.domain;

import java.util.Objects;

public class LimitedCapacity implements Capacity {
    private static final String MAX_MUST_OVER_1 = "최대 인원은 1명 이상이어야 합니다.";
    private static final String CURRENT_BETWEEN_0_AND_MAX = "현재 인원은 0 이상 최대 인원 이하이어야 합니다.";
    public static final String THERE_IS_NO_ROOM = "정원이 초과되어 수강 신청이 불가능합니다.";
    private final int max;
    private final int current;

    public LimitedCapacity(int max) {
        this(max, 0);
    }

    public LimitedCapacity(int max, int current) {
        validate(max, current);
        this.max = max;
        this.current = current;
    }

    private void validate(int max, int current) {
        if (max < 1) {
            throw new IllegalArgumentException(MAX_MUST_OVER_1);
        }
        if (current < 0 || current > max) {
            throw new IllegalArgumentException(CURRENT_BETWEEN_0_AND_MAX);
        }
    }

    @Override
    public boolean hasRoom() {
        return current < max;
    }

    @Override
    public int remaining() {
        return max - current;
    }

    @Override
    public Capacity increase() {
        if (!hasRoom()) {
            throw new IllegalStateException(THERE_IS_NO_ROOM);
        }
        return new LimitedCapacity(max, current + 1);
    }

    @Override
    public int getCurrent() {
        return current;
    }

    @Override
    public int getMax() {
        return max;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LimitedCapacity that = (LimitedCapacity) o;
        return max == that.max && current == that.current;
    }

    @Override
    public int hashCode() {
        return Objects.hash(max, current);
    }
}
