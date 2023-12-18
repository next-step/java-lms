package nextstep.courses.service;

import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionType;
import nextstep.users.domain.NsUser;

public class EnrollSessionService {
    public void enrollSession(Session session, NsUser student) {
        if (SessionType.isFree(session.getSessionType())) {
            session.signUp(student);

        } else {
            session.signUp(student);
        }

    }
}
