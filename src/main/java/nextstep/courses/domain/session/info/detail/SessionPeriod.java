package nextstep.courses.domain.session.info.detail;

import java.time.LocalDateTime;

public class SessionPeriod {
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    public SessionPeriod(LocalDateTime startDate, LocalDateTime endDate) {
        validateDates(startDate, endDate);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private void validateDates(LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("시작일과 종료일은 필수입니다.");
        }
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("시작일은 종료일보다 이전이어야 합니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionPeriod that = (SessionPeriod) o;
        return startDate.equals(that.startDate) && endDate.equals(that.endDate);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(startDate, endDate);
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }
} 