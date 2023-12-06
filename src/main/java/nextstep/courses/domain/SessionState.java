package nextstep.courses.domain;

import nextstep.courses.domain.startegy.sessionStateStrategy.SessionStateStrategy;

import java.time.LocalDate;

public enum SessionState {
    PREPARING("준비중", (startDate, endDate, currentDate) -> startDate.isAfter(currentDate)),
    RECRUITING("모집중", (startDate, endDate, currentDate) -> startDate.isAfter(currentDate)),
    END("종료", (startDate, endDate, currentDate) -> endDate.isBefore(currentDate));

    private final String state;
    private final SessionStateStrategy sessionStateStrategy;

    SessionState (String state, SessionStateStrategy sessionStateStrategy) {
        this.state = state;
        this.sessionStateStrategy = sessionStateStrategy;
    }

    public boolean checkStatus(LocalDate startDate, LocalDate endDate, LocalDate currentDate) {
        return this.sessionStateStrategy.checkStatus(startDate, endDate, currentDate);
    }



}
