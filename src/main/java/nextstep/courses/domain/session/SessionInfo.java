package nextstep.courses.domain.session;

import nextstep.courses.type.SessionType;

import java.time.LocalDate;

public class SessionInfo {
    private final SessionType sessionType;
    private final Period period;

    private SessionInfo(SessionType sessionType, Period period) {
        this.sessionType = sessionType;
        this.period = period;
    }

    public static SessionInfo of(SessionType sessionType, Period period) {
        return new SessionInfo(sessionType, period);
    }

    public static SessionInfo of(String sessionType, LocalDate startDate, LocalDate endDate) {
        return new SessionInfo(SessionType.valueOf(sessionType), Period.of(startDate, endDate));
    }

    public String sessionTypeValue() {
        return sessionType.name();
    }

    public LocalDate startDate() {
        return period.startDate();
    }

    public LocalDate endDate() {
        return period.endDate();
    }
}
