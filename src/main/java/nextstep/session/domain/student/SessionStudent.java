package nextstep.session.domain.student;

import nextstep.BaseTime;
import nextstep.users.domain.NsUser;

import java.util.Objects;

public class SessionStudent {
    private final Long id;
    private final Long sessionId;
    private final Long nsUserId;
    private SessionStudentStatus status;
    private BaseTime baseTime;

    public SessionStudent(Long id, Long sessionId, Long nsUserId, SessionStudentStatus status) {
        this.id = id;
        this.sessionId = sessionId;
        this.nsUserId = nsUserId;
        this.status = status;
        this.baseTime = new BaseTime();
    }

    public SessionStudent(Long id, Long sessionId, NsUser nsUser) {
        this.id = id;
        this.sessionId = sessionId;
        this.nsUserId = nsUser.getId();
        this.status = SessionStudentStatus.REQUEST;
    }

    public SessionStudentStatus getStatus() {
        return status;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "SessionStudent{" +
                "id=" + id +
                ", sessionId=" + sessionId +
                ", nsUserId=" + nsUserId +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionStudent that = (SessionStudent) o;
        return Objects.equals(id, that.id) && Objects.equals(sessionId, that.sessionId) && Objects.equals(nsUserId, that.nsUserId) && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sessionId, nsUserId, status);
    }
}
