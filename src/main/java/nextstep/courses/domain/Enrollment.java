package nextstep.courses.domain;

import nextstep.courses.enums.SessionStatus;
import nextstep.users.domain.NsUser;

public class Enrollment {
    private SessionStatus sessionStatus;
    private NsUsers nsUsers;

    public Enrollment(SessionStatus sessionStatus, NsUsers nsUsers) {
        this.sessionStatus = sessionStatus;
        this.nsUsers = nsUsers;
    }

    public Enrollment enrollNsUser(NsUser nsUser) {
        if (sessionStatus != SessionStatus.RECRUITING) {
            throw new IllegalArgumentException("강의가 모집중 일때만 등록 가능합니다.");
        }
        nsUsers.enroll(nsUser);
        return this;
    }


    public int countNsUsers() {
        return nsUsers.count();
    }
}
