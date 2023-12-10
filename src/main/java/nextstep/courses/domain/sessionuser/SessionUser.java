package nextstep.courses.domain.sessionuser;

import nextstep.courses.domain.session.Session;
import nextstep.users.domain.NsUser;

public class SessionUser {
    private NsUser nsUser;
    private Session session;

    public SessionUser(NsUser nsUser, Session session) {
        this.nsUser = nsUser;
        this.session = session;
    }

    public NsUser getNsUser() {
        return nsUser;
    }

    public Session getSession() {
        return session;
    }
}
