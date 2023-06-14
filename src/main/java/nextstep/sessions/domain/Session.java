package nextstep.sessions.domain;

import nextstep.BaseTime;
import nextstep.courses.domain.Course;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.Objects;

public class Session {

    private Long id;
    private Long courseId;
    private SessionPeriod sessionPeriod;
    private SessionPaymentType sessionPaymentType;
    private SessionStatus sessionStatus;
    private SessionStudents sessionStudents;
    private SessionCoverImage sessionCoverImage;
    private BaseTime baseTime;

    private Course course;

    public Session(Long id, Course course, LocalDateTime startDate, LocalDateTime endDate, SessionPaymentType sessionPaymentType, SessionStatus sessionStatus, int maximumCapacity) {
        this(id, course.getId(), startDate, endDate, sessionPaymentType, sessionStatus, maximumCapacity);
        this.course = course;
    }


    public Session(Session session, SessionStudents students) {
        this(session.getId(), session.getCourseId(), session.getSessionPeriod().getStartDate(), session.getSessionPeriod().getEndDate(),
                session.getSessionPaymentType(), session.getSessionStatus(), session.getSessionStudents().getMaximumCapacity());
        this.sessionCoverImage = session.getSessionCoverImage();
        this.sessionStudents = students;
        this.baseTime = new BaseTime();
    }


    public Session(Session session, SessionCoverImage coverImage) {
        this(session.getId(), session.getCourseId(), session.getSessionPeriod().getStartDate(), session.getSessionPeriod().getEndDate(),
                session.getSessionPaymentType(), session.getSessionStatus(), session.getSessionStudents().getMaximumCapacity());
        this.sessionStudents = session.getSessionStudents();
        this.sessionCoverImage = coverImage;
        this.baseTime = new BaseTime();
    }

    public Session(Long id, Long courseId, LocalDateTime startDate, LocalDateTime endDate, SessionPaymentType sessionPaymentType, SessionStatus sessionStatus, int maximumCapacity) {
        this.id = id;
        this.courseId = courseId;
        this.sessionPeriod = new SessionPeriod(startDate, endDate);
        this.sessionPaymentType = sessionPaymentType;
        this.sessionStatus = sessionStatus;
        this.sessionStudents = new SessionStudents(maximumCapacity);
        this.baseTime = new BaseTime();
    }

    public void enrollStudent(NsUser nsUser) {
        if (!sessionStatus.isRecruitable()) {
            throw new IllegalArgumentException("모집중일때만 신청 가능하다");
        }
        sessionStudents.enrollStudent(nsUser);
    }

    public void saveCoverImage(SessionCoverImage image) {
        if (getSessionCoverImage() != null) {
            throw new IllegalArgumentException("커버 사진은 한 장만 등록할 수 있습니다");
        }
        sessionCoverImage = new SessionCoverImage(image);
    }

    public Long getCourseId() {
        return courseId;
    }

    public SessionCoverImage getSessionCoverImage() {
        return sessionCoverImage;
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

    public Long getId() {
        return id;
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
                ", sessionCoverImage=" + sessionCoverImage +
                ", course=" + course +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return Objects.equals(id, session.id) && Objects.equals(courseId, session.courseId) && Objects.equals(sessionPeriod, session.sessionPeriod) && sessionPaymentType == session.sessionPaymentType && sessionStatus == session.sessionStatus && Objects.equals(sessionStudents, session.sessionStudents) && Objects.equals(sessionCoverImage, session.sessionCoverImage) && Objects.equals(course, session.course);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, courseId, sessionPeriod, sessionPaymentType, sessionStatus, sessionStudents, sessionCoverImage, course);
    }
}
