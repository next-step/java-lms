package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

public class Selection {
    private final Long id;
    private final NsUser nsUser;
    private final Session session;
    private boolean hasPaid = false;

    public static Selection createNewInstance(NsUser nsUser, Session session) {
        return new Selection(0L, nsUser, session, false);
    }

    public static Selection createFromData(Long id, NsUser nsUser, Session session, boolean hasPaid) {
        return new Selection(id, nsUser, session, hasPaid);
    }

    private Selection(Long id, NsUser nsUser, Session session, boolean hasPaid) {
        this.id = id;
        this.nsUser = nsUser;
        this.session = session;
        this.hasPaid = hasPaid;
    }

    public boolean hasPaid() {
        return hasPaid;
    }
}
