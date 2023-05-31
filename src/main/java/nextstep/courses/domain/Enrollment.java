package nextstep.courses.domain;

import nextstep.courses.enums.SessionStatus;
import nextstep.users.domain.NsUser;

public class Enrollment {
    private SessionStatus sessionStatus;
    private NsUsers nsUsers;

    private int maxUserCount;


    public Enrollment(SessionStatus sessionStatus, NsUsers nsUsers, int maxUserCount) {
        this.sessionStatus = sessionStatus;
        this.nsUsers = nsUsers;
        this.maxUserCount = maxUserCount;
    }

    public Enrollment enrollNsUser(NsUser nsUser) {
        if (sessionStatus != SessionStatus.RECRUITING) {
            throw new IllegalArgumentException("강의가 모집중 일때만 등록 가능합니다.");
        }
        if (nsUsers.count() == maxUserCount) {
            throw new IllegalArgumentException("강의 최대 수강 인원이 초과되었습니다.");
        }
        nsUsers.enroll(nsUser);
        return this;
    }

    public SessionStatus getSessionStatus() {
        return sessionStatus;
    }

    public NsUsers getNsUsers() {
        return nsUsers;
    }

    public int getMaxUserCount() {
        return maxUserCount;
    }
}
