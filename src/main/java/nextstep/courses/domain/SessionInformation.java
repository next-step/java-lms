package nextstep.courses.domain;

import nextstep.courses.exception.CanNotApplyException;

import java.util.Objects;

public class SessionInformation {

    private final SessionStatus status;

    private final SessionType sessionType;

    private final Period period;

    public SessionInformation(SessionStatus status, SessionType sessionType, Period period) {
        this.status = status;
        this.sessionType = sessionType;
        this.period = period;
    }

    public void validateApply() {
        validateRecruiting();
    }

    private void validateRecruiting() {
        if (status.isNotRecruiting()) {
            throw new CanNotApplyException();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionInformation that = (SessionInformation) o;
        return status == that.status
                && sessionType == that.sessionType
                && Objects.equals(period, that.period);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, sessionType, period);
    }
}
