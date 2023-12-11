package nextstep.courses.domain.session;

import nextstep.courses.exception.SessionPeriodException;

import java.time.LocalDate;
import java.util.Objects;

public class SessionPeriod {
    private LocalDate startDate;

    private LocalDate endDate;

    public SessionPeriod(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void checkSessionStatus(SessionState sessionState) {
        if(!sessionState.checkStatus(startDate, endDate, LocalDate.now())){
            throw new SessionPeriodException("강의 시작일, 종료일과 강의 상태가 맞지 않습니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionPeriod that = (SessionPeriod) o;
        return Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate);
    }

    @Override
    public String toString() {
        return "SessionPeriod{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
