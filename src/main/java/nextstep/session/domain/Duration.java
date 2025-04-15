package nextstep.session.domain;

import java.time.LocalDate;
import java.util.Objects;

public class Duration {
    private final LocalDate startAt;
    private final LocalDate endAt;

    public Duration(LocalDate startAt, LocalDate endAt) {
        if (startAt == null || endAt == null) {
            throw new IllegalArgumentException("시작일과 종료일은 필수입니다.");
        }
        if (endAt.isBefore(startAt)) {
            throw new IllegalArgumentException("종료일은 시작일보다 이후여야 합니다.");
        }
        this.startAt = startAt;
        this.endAt = endAt;
    }

    public LocalDate getStartAt() {
        return startAt;
    }

    public LocalDate getEndAt() {
        return endAt;
    }

    public boolean contains(LocalDate date) {
        return !date.isBefore(startAt) && !date.isAfter(endAt);
    }

    public int days() {
        return startAt.until(endAt).getDays() + 1;
    }

    public boolean canBeRefund(LocalDate date) {
        return date.isBefore(endAt);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Duration duration = (Duration) o;
        return Objects.equals(startAt, duration.startAt) && Objects.equals(endAt, duration.endAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startAt, endAt);
    }
}