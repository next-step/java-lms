package nextstep.courses.domain.session;

import nextstep.courses.exception.SessionException;
import nextstep.courses.exception.SessionPeriodException;

import java.time.LocalDate;
import java.util.Objects;

public class SessionPeriod {

    private LocalDate startDate;

    private LocalDate endDate;

    private SessionState sessionState;

    public SessionPeriod(LocalDate startDate, LocalDate endDate, SessionState sessionState) {
        this(startDate, endDate);

        checkSessionStatus(sessionState);
        this.sessionState = sessionState;
    }

    public SessionPeriod(LocalDate startDate, LocalDate endDate) {
        if(!endDate.isAfter(startDate)) {
            throw new SessionPeriodException("종료일이 시작일보다 이전일 수 없습니다.");
        }
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private void checkSessionStatus(SessionState sessionState) {
        if(!sessionState.checkStatus(startDate, endDate, LocalDate.now())){
            throw new SessionPeriodException("강의 시작일, 종료일과 강의 상태가 맞지 않습니다.");
        }
    }

    public void checkAbleToEnroll() {
        if(!SessionState.isAbleToEnroll(sessionState)) {
            throw new SessionException("모집중인 강의가 아닙니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionPeriod that = (SessionPeriod) o;
        return Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate) && sessionState == that.sessionState;
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate, sessionState);
    }

    @Override
    public String toString() {
        return "SessionPeriod{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                ", sessionState=" + sessionState +
                '}';
    }
}
