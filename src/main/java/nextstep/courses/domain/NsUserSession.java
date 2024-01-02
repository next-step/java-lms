package nextstep.courses.domain;

import java.util.Objects;

public class NsUserSession {
    private long sessionId;
    private long nsUserId;
    private EnrollmentStatus enrollmentStatus;

    public NsUserSession(long sessionId, long nsUserId) {
        this(sessionId, nsUserId, EnrollmentStatus.WAITING);
    }

    public NsUserSession(long sessionId, long nsUserId, String enrollmentStatus) {
        this(sessionId, nsUserId, EnrollmentStatus.valueOf(enrollmentStatus));
    }

    public NsUserSession(long sessionId, long nsUserId, EnrollmentStatus enrollmentStatus) {
        this.sessionId = sessionId;
        this.nsUserId = nsUserId;
        this.enrollmentStatus = enrollmentStatus;
    }

    public Long sessionId() {
        return sessionId;
    }

    public Long nsUserId() {
        return nsUserId;
    }

    public EnrollmentStatus enrollmentStatus() {
        return enrollmentStatus;
    }

    public boolean isApproved() {
        return enrollmentStatus == EnrollmentStatus.APPROVED;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NsUserSession that = (NsUserSession) o;
        return sessionId == that.sessionId && nsUserId == that.nsUserId && enrollmentStatus == that.enrollmentStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessionId, nsUserId, enrollmentStatus);
    }

    @Override
    public String toString() {
        return "NsUserSession{" +
                "sessionId=" + sessionId +
                ", nsUserId=" + nsUserId +
                ", enrollmentStatus=" + enrollmentStatus +
                '}';
    }

}
