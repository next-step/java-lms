package nextstep.courses.domain.session;

import java.util.ArrayList;
import java.util.List;

public class SessionEnrollment {

    private SessionStatus sessionStatus;
    private List<SessionUser> sessionUsers;
    private int maxUserSize;

    public SessionEnrollment(SessionStatus sessionStatus, int maxUserSize) {
        this(sessionStatus, new ArrayList<>(), maxUserSize);
    }

    public SessionEnrollment() {
        this(SessionStatus.PREPARING, 0);
    }

    public SessionEnrollment(SessionStatus sessionStatus, List<SessionUser> sessionUsers, int maxUserSize) {
        this.sessionStatus = sessionStatus;
        this.sessionUsers = sessionUsers;
        this.maxUserSize = maxUserSize;
    }


    public void enroll(SessionUser user) {
        validate2();
        sessionUsers.add(user);
    }

    private void validate2() {
        if (sessionStatus != SessionStatus.RECRUITING) {
            throw new IllegalStateException("모집중이 아닙니다.");
        }
        if (maxUserSize <= sessionUsers.size()) {
            throw new IllegalStateException("모집인원이 다 찼습니다.");
        }
    }

    public String getSessionStatus() {
        return sessionStatus.name();
    }

    public int getMaxUserSize() {
        return maxUserSize;
    }
}
