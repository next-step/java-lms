package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.Objects;

public class SessionJoin {
    private Long id;
    private final Session session;
    private final NsUser nsUser;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public SessionJoin(Session session, NsUser nsUser) {
        this.session = session;
        this.nsUser = nsUser;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public SessionJoin(Session session, NsUser nsUser, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.session = session;
        this.nsUser = nsUser;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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
}
