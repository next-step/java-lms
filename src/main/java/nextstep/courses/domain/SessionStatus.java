package nextstep.courses.domain;

import nextstep.courses.CannotEnrollStatusException;

public enum SessionStatus {
    READY, RECRUITING, END;

    public void enableEnrollment(SessionStatus sessionStatus) {
        if (!sessionStatus.equals(RECRUITING)) {
            throw new CannotEnrollStatusException(CannotEnrollStatusException.MESSAGE);
        }
    }
}
