package nextstep.courses.domain;

import nextstep.courses.domain.startegy.sessionStateStartegy.SessionStateStartegy;

import java.time.LocalDate;

public enum SessionState {
    PREPARING("준비중", (startDate, endDate, currentDate) -> startDate.isAfter(currentDate)),
    RECRUITING("모집중", (startDate, endDate, currentDate) -> startDate.isAfter(currentDate)),
    END("종료", (startDate, endDate, currentDate) -> endDate.isBefore(currentDate));

    private final String status;
    private final SessionStateStartegy sessionStateStartegy;

    SessionState (String status, SessionStateStartegy sessionStateStartegy) {
        this.status = status;
        this.sessionStateStartegy = sessionStateStartegy;
    }

    public boolean checkStatus(LocalDate startDate, LocalDate endDate, LocalDate currentDate) {
        return this.sessionStateStartegy.checkStatus(startDate, endDate, currentDate);
    }

}
