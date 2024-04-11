package nextstep.courses.domain.enrollment.engine;

import nextstep.courses.domain.enrollment.SessionCapacity;
import nextstep.courses.domain.enrollment.SessionFee;
import nextstep.courses.domain.enrollment.SessionStatus;
import nextstep.courses.domain.enrollment.Student;
import nextstep.payments.domain.Payment;

import java.util.ArrayList;
import java.util.List;

public abstract class ConcreteSessionEnrollment implements SessionEnrollment {

    protected final Long id;
    protected final Long sessionId;
    protected final SessionStatus status;
    protected final SessionCapacity capacity;
    protected final SessionFee fee;
    protected final List<Student> students;

    protected ConcreteSessionEnrollment(Long id, Long sessionId, SessionStatus status, int capacity, long fee) {
        this.id = id;
        this.sessionId = sessionId;
        this.status = status;
        this.capacity = new SessionCapacity(id, sessionId, capacity);
        this.fee = new SessionFee(id, sessionId, fee);
        this.students = new ArrayList<>();
    }

    @Override
    public void enroll(Student student, Payment payment) {
        satisfyEnrollment(payment);
        students.add(student);
    }

    @Override
    public void satisfyStatus() {
        status.canEnroll();
    }

    @Override
    public void satisfyCapacity() {
        capacity.hasCapacity(students.size());
    }

}
