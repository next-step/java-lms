package nextstep.Session;

import java.time.LocalDateTime;

public class SessionDuration {

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public SessionDuration(final LocalDateTime startDate, final LocalDateTime endDate) {
        validateDuration(startDate, endDate);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private void validateDuration(final LocalDateTime startDate, final LocalDateTime endDate) {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("시작일은 종료일보다 작을 수 없습니다.");
        }
    }

    public boolean isStarted() {
        return LocalDateTime.now().isAfter(startDate);
    }
}
