package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class SessionPeriod {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public SessionPeriod(LocalDateTime startTime, LocalDateTime endTime) {
        validationPeriod(startTime, endTime);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public LocalDateTime getStartTime() {
        return this.startTime;
    }

    public LocalDateTime getEndTime() {
        return this.endTime;
    }

    private void validationPeriod(LocalDateTime startTime, LocalDateTime endTime) {
        if (startTime == null || endTime == null) {
            throw new IllegalArgumentException("시작 시간과 종료 시간은 필수입니다.");
        }

        if (endTime.isBefore(startTime)) {
            throw new IllegalArgumentException("종료 시간은 시작 시간 이후여야 합니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SessionPeriod that = (SessionPeriod) o;
        return Objects.equals(startTime, that.startTime) && Objects.equals(endTime, that.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startTime, endTime);
    }

    @Override
    public String toString() {
        return "SessionPeriod{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
