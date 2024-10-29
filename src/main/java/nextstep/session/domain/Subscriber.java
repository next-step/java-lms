package nextstep.session.domain;

import nextstep.DateDomain;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Subscriber {

    private Long id;
    private Session session;
    private NsUser nsUser;
    private DateDomain dateDomain;

    public Subscriber(Session session, NsUser nsUser) {
        this.session = session;
        this.nsUser = nsUser;
        this.dateDomain = new DateDomain();
    }

    public Subscriber(Long id, Session session, NsUser nsUser, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.session = session;
        this.nsUser = nsUser;
        this.dateDomain = new DateDomain(createdAt, updatedAt);
    }

    public Subscriber(Long id, NsUser nsUser, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.nsUser = nsUser;
        this.dateDomain = new DateDomain(createdAt, updatedAt);
    }

    public Long getId() {
        return id;
    }

    public NsUser getNsUser() {
        return nsUser;
    }

    public DateDomain getDateDomain() {
        return dateDomain;
    }

    public Session getSession() {
        return session;
    }
}
