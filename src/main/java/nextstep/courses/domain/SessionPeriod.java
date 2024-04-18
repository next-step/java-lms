package nextstep.courses.domain;

import java.time.LocalDateTime;

public class SessionPeriod {
    private LocalDateTime startDate;

    private LocalDateTime endDate;

    public SessionPeriod(LocalDateTime startDate, LocalDateTime endDate){
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("시작일이 종료일보다 나중일 수 없습니다.");
        }
        this.startDate = startDate;
        this.endDate = endDate;
    }


}
