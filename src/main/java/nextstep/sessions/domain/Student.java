package nextstep.sessions.domain;

import java.util.Objects;

public class Student {
    private final long nsUserId;
    private final long sessionId;
    private boolean isApproved; //승인 상태

    public Student(long nsUserId, long sessionId) {
        this(nsUserId, sessionId, true);
    }

    public Student(long nsUserId, long sessionId, boolean isApproved) {
        this.nsUserId = nsUserId;
        this.sessionId = sessionId;
        this.isApproved = isApproved;
    }

    public void approve() {
        this.isApproved = true;
    }

    public void disApprove() {
        this.isApproved = false;
    }

    public long getId() {
        return nsUserId;
    }

    public long getNsUserId() {
        return nsUserId;
    }

    public long getSessionId() {
        return sessionId;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        Student student = (Student) object;
        return nsUserId == student.nsUserId && sessionId == student.sessionId && isApproved == student.isApproved;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nsUserId, sessionId, isApproved);
    }
}
