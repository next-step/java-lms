package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

public class Registration {

    private Long id = null;
    private final NsUser user;
    private final Session session;
    private final Long amount;

    private Registration(Long id, NsUser user, Session session, Long amount) {
        this.id = id;
        this.user = user;
        this.session = session;
        this.amount = amount;
    }

    private Registration(NsUser user, Session session, Long amount) {
        this.user = user;
        this.session = session;
        this.amount = amount;
    }

    public static Registration register(NsUser user, Session session, Long amount) {
        Registration registration = new Registration(user, session, amount);
        session.enroll(user, amount);
        return registration;
    }

    public void register() {
        session.enroll(user, amount);
    }

    public Long id() {
        return id;
    }

    public NsUser nsUser() {
        return user;
    }

    public Session session() {
        return session;
    }

    public Long amount() {
        return amount;
    }
}
