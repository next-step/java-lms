package nextstep.courses.domain;

import java.time.LocalDate;
import java.util.Objects;

public class SessionPeriod {
    private static final String NOT_ALLOW_NULL = "시작일과 종료일은 null일 수 없습니다.";
    private static final String START_AT_MUST_BEFORE_END_AT = "시작일은 종료일보다 이후일 수 없습니다.";
    private final LocalDate startAt;
    private final LocalDate endAt;

    public SessionPeriod(LocalDate start, LocalDate end) {
        validate(start, end);
        this.startAt = start;
        this.endAt = end;
    }

    private void validate(LocalDate start, LocalDate end) {
        if (start == null || end == null) {
            throw new IllegalArgumentException(NOT_ALLOW_NULL);
        }
        if (start.isAfter(end)) {
            throw new IllegalArgumentException(START_AT_MUST_BEFORE_END_AT);
        }
    }

    public LocalDate getStartAt() {
        return startAt;
    }

    public LocalDate getEndAt() {
        return endAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SessionPeriod that = (SessionPeriod) o;
        return Objects.equals(startAt, that.startAt) && Objects.equals(endAt, that.endAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startAt, endAt);
    }
}
