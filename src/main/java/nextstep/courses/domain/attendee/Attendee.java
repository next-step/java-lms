package nextstep.courses.domain.attendee;

import nextstep.courses.exception.AlreadyApprovedException;
import nextstep.courses.exception.AlreadyNotApprovedException;

import java.util.Objects;

import static nextstep.courses.domain.attendee.Approval.*;

public class Attendee {

    private final Long studentId;

    private final Long sessionId;

    private final Approval approval;

    public Attendee(Long studentId,
                    Long sessionId,
                    Approval approval) {
        this.studentId = studentId;
        this.sessionId = sessionId;
        this.approval = approval;
    }

    public Attendee approve() {
        if (approval.isApproved()) {
            throw new AlreadyApprovedException();
        }
        return new Attendee(this.studentId, this.sessionId, APPROVED);
    }

    public Attendee cancel() {
        if (approval.isNotApproved()) {
            throw new AlreadyNotApprovedException();
        }
        return new Attendee(this.studentId, this.sessionId, NOT_APPROVED);
    }

    public Long getStudentId() {
        return studentId;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public String getApprovalString() {
        return approval.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attendee attendee = (Attendee) o;
        return Objects.equals(studentId, attendee.studentId)
                && Objects.equals(sessionId, attendee.sessionId)
                && Objects.equals(approval, attendee.approval);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, sessionId, approval);
    }
}
