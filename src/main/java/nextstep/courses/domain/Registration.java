package nextstep.courses.domain;

import nextstep.courses.utils.BaseEntity;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Registration extends BaseEntity {
    private Long id = null;
    private final NsUser user;
    private final Session session;
    private final Long amount;

    public Registration(Long id, NsUser user, Session session, Long amount, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.user = user;
        this.session = session;
        this.amount = amount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Registration(NsUser user, Session session, Long amount) {
        this.user = user;
        this.session = session;
        this.amount = amount;
    }

    public static Registration register(NsUser user, Session session, Long amount) {
        Registration registration = new Registration(user, session, amount);
        session.enroll(user, amount);
        return registration;
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
