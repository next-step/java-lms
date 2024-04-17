package nextstep.courses.domain.enrollment.engine;

import nextstep.courses.domain.enrollment.SessionCapacity;
import nextstep.courses.domain.enrollment.SessionFee;
import nextstep.courses.domain.status.SessionStatus;
import nextstep.courses.domain.student.SessionStudent;
import nextstep.courses.domain.student.SessionStudents;
import nextstep.courses.exception.SessionCapacityExceedException;
import nextstep.courses.exception.SessionStatusCannotEnrollmentException;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public abstract class SessionEnrollment implements SessionEnroll {

    protected final SessionStatus status;
    protected final SessionCapacity capacity;
    protected final SessionFee fee;
    protected final SessionStudents students;

    protected SessionEnrollment(SessionEnrollment enrollment, List<SessionStudent> students) {
        this(enrollment.getStatus(), enrollment.getCapacity().get(), enrollment.getFee().get(), students);
    }

    protected SessionEnrollment(SessionStatus status, int capacity, long fee) {
        this(status, capacity, fee, new ArrayList<>());
    }

    protected SessionEnrollment(SessionStatus status, int capacity, long fee, List<SessionStudent> students) {
        this.status = status;
        this.capacity = new SessionCapacity(capacity);
        this.fee = new SessionFee(fee);
        this.students = new SessionStudents(students);
    }

    @Override
    public SessionStudent enroll(Long sessionId, NsUser nsUser, Payment payment) {
        satisfyEnrollment(payment);

        SessionStudent student = SessionStudent.from(sessionId, nsUser);
        students.add(student);

        return student;
    }

    @Override
    public void satisfyStatus() {
        if (status.cannotEnroll()) {
            throw new SessionStatusCannotEnrollmentException(status);
        }
    }

    @Override
    public void satisfyCapacity() {
        if (capacity.noCapacity(students.size())) {
            throw new SessionCapacityExceedException(capacity.get(), students.size());
        }
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

    public SessionStudents getStudents() {
        return students;
    }

}
