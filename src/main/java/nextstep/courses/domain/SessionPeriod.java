package nextstep.courses.domain;

import java.time.LocalDateTime;

public class SessionPeriod {

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    public SessionPeriod(LocalDateTime startTime, LocalDateTime endTime) {
        if (startTime.isAfter(endTime)) {
            throw new IllegalArgumentException("시작일은 종료일보다 빨라야합니다.");
        }
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public boolean isNotBetween(LocalDateTime time) {
        return startTime.isAfter(time) || endTime.isBefore(time);
    }

}
