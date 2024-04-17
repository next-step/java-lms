package nextstep.sessions.domain;

import nextstep.courses.domain.Course;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUsers;

public class FreeSession extends Session{

    public FreeSession(Course course, SessionPeriod sessionPeriod, SessionImage sessionImage, NsUsers nsUsers, SessionStatus sessionStatus) {
        super(course, sessionPeriod, sessionImage, nsUsers, sessionStatus);
    }

    @Override
    public void signUp(NsUser nsUser) {
        checkSessionStatus();
        this.nsUsers.add(nsUser);
    }


}
