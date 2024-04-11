package nextstep.courses.domain.enrollment.engine;

import nextstep.courses.domain.enrollment.SessionCapacity;
import nextstep.courses.domain.enrollment.SessionFee;
import nextstep.courses.domain.enrollment.SessionStatus;
import nextstep.courses.domain.enrollment.Student;
import nextstep.payments.domain.Payment;

public abstract class ConcreteSessionEnrollment implements SessionEnrollment {

    protected final Long id;
    protected final Long sessionId;
    protected final SessionStatus status;
    protected final SessionCapacity capacity;
    protected final SessionFee fee;

    protected ConcreteSessionEnrollment(Long id, Long sessionId, SessionStatus status, int capacity, long fee) {
        this.id = id;
        this.sessionId = sessionId;
        this.status = status;
        this.capacity = new SessionCapacity(id, sessionId, capacity);
        this.fee = new SessionFee(id, sessionId, fee);
    }

    @Override
    public void enroll(Student student, Payment payment) {
        satisfyEnrollment(payment);
        capacity.addStudent(student);
    }

    @Override
    public void satisfyStatus() {
        status.canEnroll();
    }

    @Override
    public void satisfyCapacity() {
        capacity.hasCapacity();
    }

}
