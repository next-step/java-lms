package nextstep.sessions.domain;

import nextstep.users.domain.NsUser;

public class FreeSession extends Session{

    public FreeSession(Long courseId, SessionPeriod sessionPeriod, SessionImage sessionImage, SessionStatus sessionStatus) {
        super(courseId, sessionPeriod, sessionImage, sessionStatus);
    }

    @Override
    public void signUp(NsUser nsUser) {
        checkSessionStatus();
        this.nsUsers.add(nsUser);
    }


}
