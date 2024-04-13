package nextstep.courses.domain;

import java.time.LocalDateTime;

public class SessionSchedule {

    private LocalDateTime startAt;

    private LocalDateTime endAt;

    public SessionSchedule() {
    }

    public SessionSchedule(LocalDateTime startAt, LocalDateTime endAt) {
        validateSchedule(startAt, endAt);
        this.startAt = startAt;
        this.endAt = endAt;
    }

    private void validateSchedule(LocalDateTime startAt, LocalDateTime endAt) {
        if (startAt.isAfter(endAt)) {
            throw new IllegalArgumentException("강의 시작일이 종료일보다 늦을 수 없습니다.");
        }
    }

    public LocalDateTime getStartAt() {
        return startAt;
    }

    public LocalDateTime getEndAt() {
        return endAt;
    }
}
