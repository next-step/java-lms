package nextstep.courses.domain;

import java.time.LocalDateTime;

public class SessionPeriod {

    private LocalDateTime createAt;
    private LocalDateTime closeAt;

    public SessionPeriod(LocalDateTime createAt, LocalDateTime closeAt) {
        validateInterval(createAt, closeAt);
        this.createAt = createAt;
        this.closeAt = closeAt;
    }

    private void validateInterval(LocalDateTime createAt, LocalDateTime closeAt) {
        if (createAt.isAfter(closeAt)) {
            throw new IllegalArgumentException("강의 시작일과 마감일을 잘못 입력하였습니다.");
        }
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public LocalDateTime getCloseAt() {
        return closeAt;
    }

}
