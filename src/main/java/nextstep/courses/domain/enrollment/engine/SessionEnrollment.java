package nextstep.courses.domain.enrollment.engine;

import nextstep.courses.domain.enrollment.SessionCapacity;
import nextstep.courses.domain.enrollment.SessionFee;
import nextstep.courses.domain.enrollment.RecruitmentStatus;
import nextstep.courses.domain.student.SessionStudent;
import nextstep.courses.domain.student.SessionStudents;
import nextstep.courses.exception.RecruitmentStatusCannotEnrollmentException;
import nextstep.courses.exception.SessionCapacityExceedException;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public abstract class SessionEnrollment implements SessionEnroll {

    protected RecruitmentStatus recruitmentStatus;
    protected final SessionCapacity capacity;
    protected final SessionFee fee;
    protected final SessionStudents students;

    protected SessionEnrollment(SessionEnrollment enrollment, List<SessionStudent> students) {
        this(enrollment.getRecruitmentStatus(), enrollment.getCapacity().get(), enrollment.getFee().get(), students);
    }

    protected SessionEnrollment(SessionEnrollment enrollment, SessionStudents students) {
        this(enrollment.getRecruitmentStatus(), enrollment.getCapacity(), enrollment.getFee(), students);
    }

    protected SessionEnrollment(RecruitmentStatus recruitmentStatus, int capacity, long fee) {
        this(recruitmentStatus, capacity, fee, new ArrayList<>());
    }

    protected SessionEnrollment(RecruitmentStatus recruitmentStatus, int capacity, long fee, List<SessionStudent> students) {
        this.recruitmentStatus = recruitmentStatus;
        this.capacity = new SessionCapacity(capacity);
        this.fee = new SessionFee(fee);
        this.students = new SessionStudents(students);
    }

    public SessionEnrollment(RecruitmentStatus recruitmentStatus, SessionCapacity capacity, SessionFee fee, SessionStudents students) {
        this.recruitmentStatus = recruitmentStatus;
        this.capacity = capacity;
        this.fee = fee;
        this.students = students;
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
        if (recruitmentStatus.cannotEnroll()) {
            throw new RecruitmentStatusCannotEnrollmentException(recruitmentStatus);
        }
    }

    @Override
    public void satisfyCapacity() {
        if (capacity.noCapacity(students.size())) {
            throw new SessionCapacityExceedException(capacity.get(), students.size());
        }
    }

    public RecruitmentStatus getRecruitmentStatus() {
        return recruitmentStatus;
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
