package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.Objects;

public class SessionJoin {
    private Long id;
    private final Session session;
    private final NsUser nsUser;
    private SessionJoinStatus sessionJoinStatus;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static SessionJoin apply(Session session, NsUser nsUser, SessionJoinStatus status) {
        LocalDateTime now = LocalDateTime.now();
        return new SessionJoin(null, session, nsUser, status, now, null);
    }

    public SessionJoin(Long id, Session session, NsUser nsUser, SessionJoinStatus sessionJoinStatus, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.session = session;
        this.nsUser = nsUser;
        this.sessionJoinStatus = sessionJoinStatus;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public boolean isApproveStatus() {
        return sessionJoinStatus.isApproveStatus();
    }

    public void approve() {
        this.sessionJoinStatus = SessionJoinStatus.APPROVAL;
        this.updatedAt = LocalDateTime.now();
    }

    public void reject() {
        this.sessionJoinStatus = SessionJoinStatus.REJECTION;
        this.updatedAt = LocalDateTime.now();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
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
