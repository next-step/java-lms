package nextstep.courses.domain;

import java.time.LocalDateTime;

public class SessionPeriod {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public SessionPeriod(LocalDateTime startTime, LocalDateTime endTime) {
        validationPeriod(startTime, endTime);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    private void validationPeriod(LocalDateTime startTime, LocalDateTime endTime) {
        if (startTime == null || endTime == null) {
            throw new IllegalArgumentException("시작 시간과 종료 시간은 필수입니다.");
        }

        if (endTime.isBefore(startTime)) {
            throw new IllegalArgumentException("종료 시간은 시작 시간 이후여야 합니다.");
        }
    }
}
