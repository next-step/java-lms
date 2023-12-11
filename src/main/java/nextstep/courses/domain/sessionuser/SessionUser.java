package nextstep.courses.domain.sessionuser;

import nextstep.courses.domain.session.Session;
import nextstep.users.domain.NsUser;

public class SessionUser {

    private final NsUser nsUser;
    private final Session session;

    public SessionUser(NsUser nsUser, Session session) {
        this.nsUser = nsUser;
        this.session = session;
    }

    public Session session() {
        return session;
    }
}
