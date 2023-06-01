package nextstep.sessions.domain;

import nextstep.BaseTime;
import nextstep.courses.domain.Course;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;

public class Session {

    private Long id;
    private Long courseId;
    private SessionPeriod sessionPeriod;
    private SessionPaymentType sessionPaymentType;
    private SessionStatus sessionStatus;
    private SessionStudents sessionStudents;
    private BaseTime baseTime;

    private Course course;

    public Session(Long courseId, LocalDateTime startDate, LocalDateTime endDate, SessionPaymentType sessionPaymentType, SessionStatus sessionStatus, int maximumCapacity) {
        this(0L, courseId, startDate, endDate, sessionPaymentType, sessionStatus, maximumCapacity);
    }

    public Session(Long id, Course course, LocalDateTime startDate, LocalDateTime endDate, SessionPaymentType sessionPaymentType, SessionStatus sessionStatus, int maximumCapacity) {
        this(id, course.getId(), startDate, endDate, sessionPaymentType, sessionStatus, maximumCapacity);
        this.course = course;
    }

    public Session(Long id, Long courseId, LocalDateTime startDate, LocalDateTime endDate, SessionPaymentType sessionPaymentType, SessionStatus sessionStatus, int maximumCapacity) {
        this.id = id;
        this.courseId = courseId;
        this.sessionPeriod = new SessionPeriod(startDate, endDate);
        this.sessionPaymentType = sessionPaymentType;
        this.sessionStatus = sessionStatus;
        this.sessionStudents = new SessionStudents(new HashSet<>(), maximumCapacity);
        this.baseTime = new BaseTime();
    }

    public void enrollSession(NsUser student) {
        if (!sessionStatus.isRecruitable()) {
            throw new IllegalArgumentException("모집중일때만 신청 가능하다");
        }

        sessionStudents.enrollStudent(student);
    }

    public Long getCourseId() {
        return courseId;
    }

    public SessionStudents getSessionStudents() {
        return sessionStudents;
    }

    public SessionPaymentType getSessionPaymentType() {
        return sessionPaymentType;
    }

    public SessionStatus getSessionStatus() {
        return sessionStatus;
    }

    public SessionPeriod getSessionPeriod() {
        return sessionPeriod;
    }

    public BaseTime getBaseTime() {
        return baseTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return Objects.equals(id, session.id) && Objects.equals(courseId, session.courseId) && Objects.equals(sessionPeriod, session.sessionPeriod) && sessionPaymentType == session.sessionPaymentType && sessionStatus == session.sessionStatus && Objects.equals(sessionStudents, session.sessionStudents) && Objects.equals(baseTime, session.baseTime) && Objects.equals(course, session.course);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, courseId, sessionPeriod, sessionPaymentType, sessionStatus, sessionStudents, baseTime, course);
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", courseId=" + courseId +
                ", sessionPeriod=" + sessionPeriod +
                ", sessionPaymentType=" + sessionPaymentType +
                ", sessionStatus=" + sessionStatus +
                ", sessionStudents=" + sessionStudents +
                ", baseTime=" + baseTime +
                ", course=" + course +
                '}';
    }
}
