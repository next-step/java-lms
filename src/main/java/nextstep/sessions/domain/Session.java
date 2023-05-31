package nextstep.sessions.domain;

import nextstep.courses.domain.Course;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class Session {

    private Long id;
    private Long courseId;
    private SessionPeriod sessionPeriod;
    private SessionType sessionType;
    private SessionStatus sessionStatus;
    private SessionCapacity sessionCapacity;
    private SessionStudents sessionStudents;

    private Course course;

    public Session(Long courseId, LocalDateTime startDate, LocalDateTime endDate, SessionType sessionType, SessionStatus sessionStatus, int maximumCapacity) {
        this(0L, courseId, startDate, endDate, sessionType, sessionStatus, maximumCapacity);
    }

    public Session(Long id, Course course, LocalDateTime startDate, LocalDateTime endDate, SessionType sessionType, SessionStatus sessionStatus, int maximumCapacity) {
        this(id, course.getId(), startDate, endDate, sessionType, sessionStatus, maximumCapacity);
        this.course = course;
    }

    public Session(Long id, Long courseId, LocalDateTime startDate, LocalDateTime endDate, SessionType sessionType, SessionStatus sessionStatus, int maximumCapacity) {
        this.id = id;
        this.courseId = courseId;
        this.sessionPeriod = new SessionPeriod(startDate, endDate);
        this.sessionType = sessionType;
        this.sessionStatus = sessionStatus;
        this.sessionCapacity = new SessionCapacity(maximumCapacity);
        this.sessionStudents = new SessionStudents(new ArrayList<>());
    }

    public void enrollSession(NsUser student) {
        if (!sessionStatus.isRecruitable()) {
            throw new IllegalArgumentException("모집중일때만 신청 가능하다");
        }

        if (canRecruitStudent()) {
            throw new IllegalArgumentException("정원수를 초과했습니다");
        }

        sessionStudents.enrollStudent(student);
    }

    private boolean canRecruitStudent() {
        return sessionCapacity.getMaximumCapacity() < sessionStudents.getCurrentStudentCount();
    }

    public int getStudentsNumbers() {
        return sessionStudents.getCurrentStudentCount();
    }

    public Long getCourseId() {
        return courseId;
    }

    public SessionType getSessionType() {
        return sessionType;
    }

    public SessionStatus getSessionStatus() {
        return sessionStatus;
    }

    public SessionCapacity getSessionCapacity() {
        return sessionCapacity;
    }

    public SessionPeriod getSessionPeriod() {
        return sessionPeriod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return Objects.equals(id, session.id) && Objects.equals(courseId, session.courseId) && Objects.equals(sessionPeriod, session.sessionPeriod) && sessionType == session.sessionType && sessionStatus == session.sessionStatus && Objects.equals(sessionCapacity, session.sessionCapacity) && Objects.equals(sessionStudents, session.sessionStudents) && Objects.equals(course, session.course);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, courseId, sessionPeriod, sessionType, sessionStatus, sessionCapacity, sessionStudents, course);
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", courseId=" + courseId +
                ", sessionPeriod=" + sessionPeriod +
                ", sessionType=" + sessionType +
                ", sessionStatus=" + sessionStatus +
                ", sessionCapacity=" + sessionCapacity +
                ", sessionStudents=" + sessionStudents +
                ", course=" + course +
                '}';
    }
}
