package nextstep.courses.domain;

import nextstep.courses.domain.startegy.sessionStateStartegy.SessionStateStartegy;

import java.time.LocalDate;
import java.time.LocalDateTime;

public enum SessionState {
    PREPARING("준비중", (comparedDate, currentDate) -> comparedDate.isAfter(currentDate)),
    RECRUITING("모집중", (comparedDate, currentDate) -> comparedDate.isAfter(currentDate)),
    END("종료", (comparedDate, currentDate) -> comparedDate.isBefore(currentDate));

    // , (matchingCount, isBonus) -> matchingCount < 3),

    private final String status;
    private final SessionStateStartegy sessionStateStartegy;

    SessionState (String status, SessionStateStartegy sessionStateStartegy) {
        this.status = status;
        this.sessionStateStartegy = sessionStateStartegy;
    }

    public boolean checkStatus(LocalDate comparedDate, LocalDate currentDate) {
        return this.sessionStateStartegy.checkStatus(comparedDate, currentDate);
    }

}
