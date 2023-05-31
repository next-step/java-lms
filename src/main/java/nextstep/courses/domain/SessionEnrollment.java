package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class SessionEnrollment {

    private SessionStatus sessionStatus;
    private List<NsUser> users = new ArrayList<>();
    private int maxUserSize;

    public SessionEnrollment(SessionStatus sessionStatus, int maxUserSize) {
        this.sessionStatus = sessionStatus;
        this.maxUserSize = maxUserSize;
    }

    public SessionEnrollment() {
        this(SessionStatus.PREPARING, 0);
    }

    public static SessionEnrollment newInstance() {
        return new SessionEnrollment();
    }


    public void enroll(NsUser user) {
        validate();
        users.add(user);
    }

    private void validate() {
        if (sessionStatus != SessionStatus.RECRUITING) {
            throw new IllegalStateException("모집중이 아닙니다.");
        }
        if (maxUserSize <= users.size()) {
            throw new IllegalStateException("모집인원이 다 찼습니다.");
        }
    }
}
