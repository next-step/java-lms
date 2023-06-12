package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.Objects;

public class SessionJoin {
    private Long id;
    private final Session session;
    private final NsUser nsUser;
    private final SessionJoinStatus sessionJoinStatus;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public SessionJoin(Session session, NsUser nsUser, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this(null, session, nsUser, SessionJoinStatus.APPLICATION, createdAt, updatedAt);
    }

    public SessionJoin(Long id, Session session, NsUser nsUser, SessionJoinStatus sessionJoinStatus, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.session = session;
        this.nsUser = nsUser;
        this.sessionJoinStatus = sessionJoinStatus;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public boolean isAlreadyJoin(Session session, NsUser nsUser) {
        return this.session.equals(session) && this.nsUser.equals(nsUser);
    }

    public Long getId() {
        return id;
    }

    public Session getSession() {
        return session;
    }

    public NsUser getNsUser() {
        return nsUser;
    }

    public SessionJoinStatus getSessionJoinStatus() {
        return sessionJoinStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SessionJoin that = (SessionJoin) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "SessionJoin{" + "id=" + id + ", session=" + session + ", nsUser=" + nsUser + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + '}';
    }
}
