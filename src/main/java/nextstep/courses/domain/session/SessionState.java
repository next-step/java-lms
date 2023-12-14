package nextstep.courses.domain.session;

import nextstep.courses.domain.startegy.sessionStateStrategy.SessionStateStrategy;

import java.time.LocalDate;

public enum SessionState {
    PREPARING((startDate, endDate, currentDate) -> startDate.isAfter(currentDate)),
    RECRUITING((startDate, endDate, currentDate) -> startDate.isAfter(currentDate)),
    END((startDate, endDate, currentDate) -> endDate.isBefore(currentDate));

    private final SessionStateStrategy sessionStateStrategy;

    SessionState (SessionStateStrategy sessionStateStrategy) {
        this.sessionStateStrategy = sessionStateStrategy;
    }

    public boolean checkStatus(LocalDate startDate, LocalDate endDate, LocalDate currentDate) {
        return this.sessionStateStrategy.checkStatus(startDate, endDate, currentDate);
    }

    public static boolean isAbleToEnroll(SessionState sessionState) {
        return sessionState == RECRUITING;
    }
}
