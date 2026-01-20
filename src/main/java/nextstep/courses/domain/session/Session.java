package nextstep.courses.domain.session;

import java.time.LocalDate;
import java.time.LocalDateTime;
import nextstep.core.domain.SoftDeletableBaseEntity;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public class Session extends SoftDeletableBaseEntity {
    private Long courseId;
    private Students students = new Students();
    private Period period;
    private SessionStatus sessionStatus;
    private EnrollmentPolicy enrollmentPolicy;

    public Session(Long courseId, Period period, SessionStatus sessionStatus,
        EnrollmentPolicy enrollmentPolicy) {
        this(0L, courseId, period, sessionStatus, enrollmentPolicy, LocalDateTime.now(), null);
    }

    public Session(Long id, Long courseId, Period period, SessionStatus sessionStatus,
        EnrollmentPolicy enrollmentPolicy, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, createdAt, updatedAt);
        this.courseId = courseId;
        this.period = period;
        this.sessionStatus = sessionStatus;
        this.enrollmentPolicy = enrollmentPolicy;
    }

    public Long getCourseId() {
        return courseId;
    }

    public Students getStudents() {
        return students;
    }

    public Period getPeriod() {
        return period;
    }

    public SessionStatus getSessionStatus() {
        return sessionStatus;
    }

    public String getSessionStatusName() {
        return sessionStatus.name();
    }

    public EnrollmentPolicy getEnrollmentPolicy() {
        return enrollmentPolicy;
    }

    public LocalDate getStartDate() {
        return period.getStartDate();
    }

    public LocalDate getEndDate() {
        return period.getEndDate();
    }

    public SessionType getSessionType() {
        return enrollmentPolicy.getSessionType();
    }

    public String getSessionTypeName() {
        return enrollmentPolicy.getSessionTypeName();
    }

    public int getCapacity() {
        return enrollmentPolicy.getCapacity();
    }

    public long getFee() {
        return enrollmentPolicy.getFee();
    }

    public void enroll(NsUser user) {
        enroll(user, null);
    }

    public void enroll(NsUser user, Payment payment) {
        validateRecruiting();
        enrollmentPolicy.validateEnrollment(students, payment);
        students.add(user);
    }

    private void validateRecruiting() {
        if (!sessionStatus.isEnrollable()) {
            throw new IllegalStateException();
        }
    }
}
