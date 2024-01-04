package nextstep.courses.domain.session;

import nextstep.courses.CannotApproveException;
import nextstep.courses.TeacherNotMatchException;
import nextstep.courses.domain.Enrollment;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.Objects;

public class Session {
    private Long id;
    private final Long courseId;
    private final Long generation;
    private final SessionPeriod sessionPeriod;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final SessionStatus sessionStatus;
    private final SessionCondition sessionCondition;
    private final boolean approvalRequired;
    private final Long teacherId;
    private CoverImages coverImages;

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

    private void validate(Long courseId) {
        if (courseId == null || courseId == 0L) {
            throw new IllegalArgumentException("과정 ID 값은 빈 값이거나 0이 올 수 없습니다.");
        }
    }

    public Enrollment enrollment() {
        return new Enrollment(sessionStatus, sessionCondition, approvalRequired);
    }

    public void matchTeacher(NsUser teacher) throws TeacherNotMatchException {
        if (!teacher.getId().equals(teacherId)) {
            throw new TeacherNotMatchException("강사 정보가 일치하지 않습니다.");
        }
    }

    public void canApprove() throws CannotApproveException {
        sessionCondition.isFull();
    }

    public void addUserNumber() {
        sessionCondition.addUserNumber();
    }

    public Long id() {
        return id;
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
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return approvalRequired == session.approvalRequired && Objects.equals(id, session.id) && Objects.equals(courseId, session.courseId) && Objects.equals(generation, session.generation) && Objects.equals(sessionPeriod, session.sessionPeriod) && Objects.equals(sessionStatus, session.sessionStatus) && Objects.equals(sessionCondition, session.sessionCondition) && Objects.equals(teacherId, session.teacherId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, courseId, generation, sessionPeriod, sessionStatus, sessionCondition, approvalRequired, teacherId);
    }
}
