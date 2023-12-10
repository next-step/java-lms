package nextstep.courses.domain.session;

import java.time.LocalDateTime;

public class SessionDuration {

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;

    public SessionDuration(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        validate(startDateTime, endDateTime);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    private void validate(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        if (startDateTime.isAfter(endDateTime) || startDateTime.isEqual(endDateTime)) {
            throw new IllegalArgumentException("시작일은 종료일보다 이전이어야 합니다");
        }
    }
}
