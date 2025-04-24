package nextstep.courses.domain.model;

import nextstep.users.domain.NsUser;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static nextstep.courses.domain.model.Timestamped.toLocalDateTime;

public class Student extends BaseEntity {
    private final NsUser user;
    private final Session session;

    public Student(NsUser user, Session session) {
        this(null, user, session, LocalDateTime.now(), LocalDateTime.now());
    }

    public Student(Long id, NsUser user, Session session, Timestamp createdAt, Timestamp updatedAt) {
        this(id, user, session, toLocalDateTime(createdAt), toLocalDateTime(updatedAt));
    }

    public Student(Long id, NsUser user, Session session, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, createdAt, updatedAt);
        this.user = user;
        this.session = session;
    }

    public void pay(Long price) {
        user.pay(price);
    }

    public NsUser getNsUser() {
        return user;
    }

    public Session getSession() {
        return session;
    }

    public boolean isEqualTo(NsUser user) {
        return this.user.equals(user);
    }
    
}
