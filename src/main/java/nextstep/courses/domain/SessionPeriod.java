package nextstep.courses.domain;


import java.time.LocalDateTime;

public class SessionPeriod {
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    SessionPeriod(LocalDateTime startDate, LocalDateTime endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        validatePeriod();
    }

    private void validatePeriod() {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("시작일이 종료일 보다 큽니다.");
        }
    }
}
