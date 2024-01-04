package nextstep.courses.domain.session;

import nextstep.courses.CannotApproveException;
import nextstep.courses.CannotEnrollException;
import nextstep.courses.domain.EnrollmentStatus;
import nextstep.courses.domain.Student;
import nextstep.courses.domain.Students;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Session {
    private Long id;
    private Long courseId;
    private Long generation;
    private SessionPeriod sessionPeriod;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private SessionStatus sessionStatus;
    private SessionCondition sessionCondition;
    private boolean approvalRequired;
    private Long teacherId;
    private CoverImages coverImages;
    private Students nsUserSessions;

    public Session(Long courseId,
                   SessionPeriod sessionPeriod,
                   SessionStatus sessionStatus,
                   SessionCondition sessionCondition,
                   boolean approvalRequired,
                   long teacherId) {
        this(0L, courseId, 0L, sessionPeriod, sessionStatus, sessionCondition, approvalRequired, teacherId);
    }

    public Session(Long id,
                   Long courseId,
                   Long generation,
                   SessionPeriod sessionPeriod,
                   SessionStatus sessionStatus,
                   SessionCondition sessionCondition,
                   boolean approvalRequired,
                   long teacherId) {
        validate(courseId);
        this.id = id;
        this.courseId = courseId;
        this.generation = generation;
        this.sessionPeriod = sessionPeriod;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = null;
        this.sessionStatus = sessionStatus;
        this.sessionCondition = sessionCondition;
        this.approvalRequired = approvalRequired;
        this.teacherId = teacherId;
    }

    public Session with(CoverImages coverImages) {
        if (coverImages.hasSameSessionIds(id)) {
            throw new IllegalArgumentException("sessionId가 일치하지 않습니다.");
        }
        this.coverImages = coverImages;
        return this;
    }

    public Session with(Students nsUserSessions) {
        this.nsUserSessions = nsUserSessions;
        return this;
    }

    private void validate(Long courseId) {
        if (courseId == null || courseId == 0L) {
            throw new IllegalArgumentException("과정 ID 값은 빈 값이거나 0이 올 수 없습니다.");
        }
    }

    public Student enroll(Payment payment) throws CannotEnrollException {
        sessionStatus.canEnroll();
        sessionCondition.match(payment);

        return new Student(payment.getSessionId(), payment.getNsUserId(), EnrollmentStatus.get(approvalRequired));
    }

    public Long courseId() {
        return courseId;
    }

    public Long generation() {
        return generation;
    }

    public SessionPeriod sessionPeriod() {
        return sessionPeriod;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }

    public SessionStatus sessionStatus() {
        return sessionStatus;
    }

    public SessionCondition sessionCondition() {
        return sessionCondition;
    }

    public boolean approvalRequired() {
        return approvalRequired;
    }

    public Long teacherId() {
        return teacherId;
    }

    public void canChangeEnrollmentStatus(NsUser teacher, Student nsUserSession) throws CannotApproveException {
        isSameTeacher(teacher);
        if (nsUserSession.isApproved()) {
            isFull();
        }
    }

    public void isFull() throws CannotApproveException {
        if (sessionCondition.compareToMax(nsUserSessions.approvedUserNumber())) {
            throw new CannotApproveException("인원을 추가로 승인할 수 없습니다.");
        }
    }

    private void isSameTeacher(NsUser teacher) throws CannotApproveException {
        if (!teacher.getId().equals(teacherId)) {
            throw new CannotApproveException("강사 정보가 일치하지 않습니다.");
        }
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", courseId=" + courseId +
                ", generation=" + generation +
                ", sessionPeriod=" + sessionPeriod +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", sessionStatus=" + sessionStatus +
                ", sessionCondition=" + sessionCondition +
                ", approvalRequired=" + approvalRequired +
                ", teacherId=" + teacherId +
                ", coverImages=" + coverImages +
                ", nsUserSessions=" + nsUserSessions +
                '}';
    }
}
