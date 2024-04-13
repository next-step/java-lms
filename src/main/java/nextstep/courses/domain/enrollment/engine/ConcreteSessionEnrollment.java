package nextstep.courses.domain.enrollment.engine;

import nextstep.courses.domain.enrollment.SessionCapacity;
import nextstep.courses.domain.enrollment.SessionFee;
import nextstep.courses.domain.enrollment.SessionStatus;
import nextstep.courses.domain.enrollment.Student;
import nextstep.courses.exception.SessionCapacityExceedException;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public abstract class ConcreteSessionEnrollment implements SessionEnrollment {

    protected final Long sessionId;
    protected final SessionStatus status;
    protected final SessionCapacity capacity;
    protected final SessionFee fee;
    protected final List<Student> students;

    protected ConcreteSessionEnrollment(Long sessionId, SessionStatus status, int capacity, long fee) {
        this.sessionId = sessionId;
        this.status = status;
        this.capacity = new SessionCapacity(sessionId, capacity);
        this.fee = new SessionFee(sessionId, fee);
        this.students = new ArrayList<>();
    }

    @Override
    public void enroll(NsUser nsUser, Payment payment) {
        satisfyEnrollment(payment);
        students.add(Student.from(sessionId, nsUser));
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

}
