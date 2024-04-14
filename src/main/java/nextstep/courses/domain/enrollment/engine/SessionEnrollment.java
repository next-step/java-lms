package nextstep.courses.domain.enrollment.engine;

import nextstep.courses.domain.enrollment.SessionCapacity;
import nextstep.courses.domain.enrollment.SessionFee;
import nextstep.courses.domain.enrollment.SessionStatus;
import nextstep.courses.domain.enrollment.SessionStudent;
import nextstep.courses.exception.SessionCapacityExceedException;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public abstract class SessionEnrollment implements SessionEnroll {

    protected Long sessionId;
    protected final SessionStatus status;
    protected final SessionCapacity capacity;
    protected final SessionFee fee;
    protected final List<SessionStudent> students;

    protected SessionEnrollment(Long sessionId, SessionEnrollment enrollment, List<SessionStudent> students) {
        this(sessionId, enrollment.getStatus(), enrollment.getCapacity().get(), enrollment.getFee().get(), students);
    }

    protected SessionEnrollment(Long sessionId, SessionStatus status, int capacity, long fee) {
        this.sessionId = sessionId;
        this.status = status;
        this.capacity = new SessionCapacity(sessionId, capacity);
        this.fee = new SessionFee(sessionId, fee);
        this.students = new ArrayList<>();
    }

    protected SessionEnrollment(Long sessionId, SessionStatus status, int capacity, long fee, List<SessionStudent> students) {
        this.sessionId = sessionId;
        this.status = status;
        this.capacity = new SessionCapacity(sessionId, capacity);
        this.fee = new SessionFee(sessionId, fee);
        this.students = students;
    }

    protected SessionEnrollment(SessionStatus status, int capacity, long fee) {
        this.status = status;
        this.capacity = new SessionCapacity(capacity);
        this.fee = new SessionFee(fee);
        this.students = new ArrayList<>();
    }

    protected SessionEnrollment(SessionStatus status, int capacity, long fee, List<SessionStudent> students) {
        this.status = status;
        this.capacity = new SessionCapacity(capacity);
        this.fee = new SessionFee(fee);
        this.students = students;
    }

    @Override
    public SessionStudent enroll(NsUser nsUser, Payment payment) {
        satisfyEnrollment(payment);

        SessionStudent student = SessionStudent.from(sessionId, nsUser);
        students.add(student);

        return student;
    }

    @Override
    public void satisfyStatus() {
        status.canEnroll();
    }

    @Override
    public void satisfyCapacity() {
        if (capacity.noCapacity(students.size())) {
            throw new SessionCapacityExceedException(capacity.get(), students.size());
        }
    }

    public Long getSessionId() {
        return sessionId;
    }

    public SessionStatus getStatus() {
        return status;
    }

    public SessionCapacity getCapacity() {
        return capacity;
    }

    public SessionFee getFee() {
        return fee;
    }

}
