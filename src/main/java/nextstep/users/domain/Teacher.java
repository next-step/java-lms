package nextstep.users.domain;

import nextstep.courses.domain.NsUserSessions;

public class Teacher {
    private NsUserSessions nsUserSessions;

    public Teacher(NsUserSessions nsUserSessions) {
        this.nsUserSessions = nsUserSessions;
    }

    public void register(long sessionId, long nsUSerId) throws IllegalArgumentException {
        if (!nsUserSessions.isExist(sessionId, nsUSerId)) {
            throw new IllegalArgumentException("선발되지 않은 학생입니다. 수강신청이 취소됩니다.");
        }
    }
}
