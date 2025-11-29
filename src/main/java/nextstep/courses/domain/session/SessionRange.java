package nextstep.courses.domain.session;

import java.time.LocalDateTime;

public class SessionRange {

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public SessionRange(LocalDateTime startDate, LocalDateTime endDate) {
        validateDateOrder(startDate, endDate);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private void validateDateOrder(LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("강의 종료일이 강의 시작일보다 빠를 수 없습니다.");
        }
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }
}
