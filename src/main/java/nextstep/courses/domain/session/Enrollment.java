package nextstep.courses.domain.session;

import nextstep.users.domain.NsUser;

import java.util.Objects;

public class Enrollment {

    private final Long id;
    private final NsUser user;
    private final Long sessionId;

    public Enrollment(NsUser user, Long sessionId) {
        this(0L, user, sessionId);
    }

    public Enrollment(Long id, NsUser user, Long sessionId) {
        this.id = id;
        this.user = user;
        this.sessionId = sessionId;
    }

    public Long getId() {
        return id;
    }

    public NsUser getUser() {
        return user;
    }

    public Long getSessionId() {
        return sessionId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Enrollment that = (Enrollment) o;
        return Objects.equals(user.getId(), that.user.getId()) && Objects.equals(sessionId, that.sessionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, sessionId);
    }

    @Override
    public String toString() {
        return "Enrollment{" +
                "id=" + id +
                ", user=" + user +
                ", sessionId=" + sessionId +
                '}';
    }
}
