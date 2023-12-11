package nextstep.courses.domain.session;

import nextstep.courses.type.SessionType;

import java.time.LocalDate;

public class SessionInfo {
    private final SessionType sessionType;
    private final Period period;

    public SessionInfo(SessionType sessionType, Period period) {
        this.sessionType = sessionType;
        this.period = period;
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
