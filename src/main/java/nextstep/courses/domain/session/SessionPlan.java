package nextstep.courses.domain.session;

import java.time.LocalDate;

public class SessionPlan {

    private SessionStatus sessionStatus;
    private LocalDate startDate;
    private LocalDate endDate;

    public SessionPlan(SessionStatus sessionStatus, LocalDate startDate, LocalDate endDate) {
        this.sessionStatus = sessionStatus;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public SessionStatus getSessionStatus() {
        return sessionStatus;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}
