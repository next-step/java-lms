package nextstep.courses.domain;

import java.time.ZonedDateTime;

/**
 * 강의 기간을 나타냅니다.
 * 불변 객체입니다.
 */
public class SessionDuration {
    private ZonedDateTime start;
    private ZonedDateTime end;

    private SessionDuration(ZonedDateTime start, ZonedDateTime end) {
        validateDuration(start, end);
        this.start = start;
        this.end = end;
    }

    private void validateDuration(ZonedDateTime start, ZonedDateTime end) {
        if (end.isBefore(start)) {
            throw new IllegalArgumentException("종료 시각이 시작 시각보다 빠를 수 없습니다.");
        }
    }

    /**
     * ISO8601 형식을 이용하여 기간 객체를 만듭니다.
     */
    public static SessionDuration fromIso8601(String start, String end) {
        ZonedDateTime startTime = ZonedDateTime.parse(start);
        ZonedDateTime endTime = ZonedDateTime.parse(end);

        return new SessionDuration(startTime, endTime);
    }
}