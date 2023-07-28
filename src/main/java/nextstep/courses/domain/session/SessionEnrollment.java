package nextstep.courses.domain.session;

import java.util.ArrayList;
import java.util.List;

public class SessionEnrollment {

    private final SessionStatus sessionStatus;
    private final SessionUsers sessionUsers;

    public SessionEnrollment() {
        this(SessionStatus.PREPARING, 0);
    }

    public SessionEnrollment(SessionStatus sessionStatus, int maxUserSize) {
        this(sessionStatus, new ArrayList<>(), maxUserSize);
    }

    public SessionEnrollment(SessionStatus sessionStatus, List<SessionUser> sessionUsers, int maxUserSize) {
        this(sessionStatus, new SessionUsers(sessionUsers, maxUserSize));
    }

    public SessionEnrollment(SessionStatus sessionStatus, SessionUsers sessionUsers) {
        this.sessionStatus = sessionStatus;
        this.sessionUsers = sessionUsers;
    }

    public SessionUser enroll(SessionUser user) {
        validate();
        return sessionUsers.enroll(user);
    }

    private void validate() {
        if (sessionStatus != SessionStatus.RECRUITING) {
            throw new IllegalStateException("모집중이 아닙니다.");
        }
    }

    public SessionStatus getSessionStatus() {
        return sessionStatus;
    }

    public int getMaxUserSize() {
        return sessionUsers.getMaxUserSize();
    }
}
