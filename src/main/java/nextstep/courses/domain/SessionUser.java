package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class SessionUser {
    private final Long id;
    private final Session session;
    private final NsUser nsUser;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public SessionUser(Session session, NsUser nsUser, LocalDateTime createdAt, LocalDateTime updatedAt){
        this(null, session, nsUser, createdAt, updatedAt);
    }

    public SessionUser(Long id, Session session, NsUser nsUser, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.session = session;
        this.nsUser = nsUser;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public long getId() {
        return id;
    }

    public Session getSession() {
        return session;
    }

    public NsUser getNsUser() {
        return nsUser;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public String toString() {
        return "SessionUser{" +
                "id=" + id +
                ", session=" + session +
                ", nsUser=" + nsUser +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
