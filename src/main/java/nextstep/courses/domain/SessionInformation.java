package nextstep.courses.domain;

import nextstep.courses.exception.CanNotApplyException;

public class SessionInformation {

    private final SessionStatus status;

    private final Period period;

    public SessionInformation(SessionStatus status, Period period) {
        this.status = status;
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
}
