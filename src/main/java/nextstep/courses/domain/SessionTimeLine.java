package nextstep.courses.domain;

import java.time.LocalDateTime;

public class SessionTimeLine {

    private LocalDateTime createAt;
    private LocalDateTime closeAt;

    public SessionTimeLine(LocalDateTime createAt, LocalDateTime closeAt) {
        validateInterval(createAt, closeAt);
        this.createAt = createAt;
        this.closeAt = closeAt;
    }

    private void validateInterval(LocalDateTime createAt, LocalDateTime closeAt) {
        if (closeAt.isBefore(createAt)) {
            throw new IllegalArgumentException("강의 마감일이 시작일보다 빠를 수 없습니다.");
        }
    }
}
