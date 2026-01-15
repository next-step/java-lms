package nextstep.courses.domain.session;

import java.time.LocalDateTime;

public class SessionPeriod {
    private final LocalDateTime start;
    private final LocalDateTime end;

    public SessionPeriod(String start, String end) {
        this(LocalDateTime.parse(start), LocalDateTime.parse(end));
    }

    public SessionPeriod(LocalDateTime start, LocalDateTime end) {
        validatePeriodRange(start, end);
        this.start = start;
        this.end = end;
    }

    public static void validatePeriodRange(LocalDateTime start, LocalDateTime end) {
        if (end.isBefore(start)) {
            throw new IllegalArgumentException("수강 종료일이 수강 시작일보다 빠를 수 없습니다.");
        }
    }

    public boolean canRegister(LocalDateTime registerTime) {
        return start.isBefore(registerTime) && end.isAfter(registerTime);
    }
}
