package nextstep.courses.domain.session;

import java.time.LocalDateTime;

public class SessionDateTime {
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public SessionDateTime(LocalDateTime startDate, LocalDateTime endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        checkValidEndDate();
    }

    private void checkValidEndDate() {
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("강의의 종료 날짜가 시작 날짜보다 빠릅니다. 시간 날짜를 확인해주세요.");
        }
    }
}
