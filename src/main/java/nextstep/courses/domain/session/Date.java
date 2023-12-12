package nextstep.courses.domain.session;

import java.time.LocalDateTime;
import nextstep.courses.exception.InvalidSessionDateException;

public class Date {

    private LocalDateTime startAt;

    private LocalDateTime endAt;

    public Date(LocalDateTime startAt, LocalDateTime endAt) throws InvalidSessionDateException {
        validateStartTimeAndEndTime(startAt, endAt);
        this.startAt = startAt;
        this.endAt = endAt;
    }

    private static void validateStartTimeAndEndTime(LocalDateTime startAt, LocalDateTime endAt)
        throws InvalidSessionDateException {
        if (startAt.isAfter(endAt)) {
            throw new InvalidSessionDateException("강의 시작일은 종료일보다 늦어질 수 없다");
        }
    }
}
