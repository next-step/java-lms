package nextstep.courses.domain;

import nextstep.courses.domain.session.RegistrationState;

public class Student {
    private long nsUserId;
    private long sessionId;
    private RegistrationState registrationState;

    public Student(long nsUserId, long sessionId, RegistrationState registrationState) {
        this.nsUserId = nsUserId;
        this.sessionId = sessionId;
        this.registrationState = registrationState;
    }

    public long getNsUserId() {
        return nsUserId;
    }

    public long getSessionId() {
        return sessionId;
    }

    public void isCanceled() {
        this.registrationState = RegistrationState.CANCELED;
    }
}
