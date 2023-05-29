package nextstep.courses.domain;

import java.time.LocalDate;

public class SessionDate {

    private static final String NOT_NULL_SESSION_STRAT_DATE = "강의 시작일은 필수입니다.";
    private static final String NOT_NULL_SESSION_END_DATE = "강의 종료일은 필수입니다.";

    private LocalDate sessionStartDate;
    private LocalDate sessionEndDate;

    public SessionDate(LocalDate sessionStartDate, LocalDate sessionEndDate) {
        validate(sessionStartDate, sessionEndDate);
        this.sessionStartDate = sessionStartDate;
        this.sessionEndDate = sessionEndDate;
    }

    private void validate(LocalDate sessionStartDate, LocalDate sessionEndDate) {
        sessionStartDateNonNull(sessionStartDate);
        sessionEndDateNonNull(sessionEndDate);
    }

    private void sessionStartDateNonNull(LocalDate sessionStartDate) {
        if (sessionStartDate == null) {
            throw new IllegalArgumentException(NOT_NULL_SESSION_STRAT_DATE);
        }
    }

    private void sessionEndDateNonNull(LocalDate sessionEndDate) {
        if (sessionEndDate == null) {
            throw new IllegalArgumentException(NOT_NULL_SESSION_END_DATE);
        }
    }

}
