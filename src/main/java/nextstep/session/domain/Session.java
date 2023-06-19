package nextstep.session.domain;

import nextstep.BaseTime;
import nextstep.courses.domain.Course;
import nextstep.session.domain.student.SessionStudent;
import nextstep.session.domain.student.SessionStudentStatus;
import nextstep.session.domain.student.SessionStudents;
import nextstep.session.domain.teacher.SessionTeachers;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.Objects;

public class Session {

    private Long id;
    private Long courseId;
    private SessionPeriod sessionPeriod;
    private SessionPaymentType paymentType;
    private SessionProgressStatus progressStatus;
    private SessionRecruitmentStatus recruitmentStatus;
    private SessionStudents sessionStudents;
    private SessionCoverImage sessionCoverImage;
    private BaseTime baseTime;

    private Course course;

    public Session(Long id, Course course, LocalDateTime startDate, LocalDateTime endDate, SessionPaymentType paymentType, SessionProgressStatus sessionProgress, SessionRecruitmentStatus recruitmentStatus, int maximumCapacity) {
        this(id, course.getId(), startDate, endDate, paymentType, sessionProgress, recruitmentStatus, maximumCapacity);
        this.course = course;
    }


    public Session(Session session, SessionStudents students) {
        this(session.getId(), session.getCourseId(), session.getSessionPeriod().getStartDate(), session.getSessionPeriod().getEndDate(),
                session.getPaymentType(), session.getProgressStatus(), session.getRecruitmentStatus(), session.getSessionStudents().getMaximumCapacity());
        this.sessionCoverImage = session.getSessionCoverImage();
        this.sessionStudents = students;
        this.baseTime = new BaseTime();
    }


    public Session(Session session, SessionCoverImage coverImage) {
        this(session.getId(), session.getCourseId(), session.getSessionPeriod().getStartDate(), session.getSessionPeriod().getEndDate(),
                session.getPaymentType(), session.getProgressStatus(), session.getRecruitmentStatus(), session.getSessionStudents().getMaximumCapacity());
        this.sessionStudents = session.getSessionStudents();
        this.sessionCoverImage = coverImage;
        this.baseTime = new BaseTime();
    }

    public Session(Long id, Long courseId, LocalDateTime startDate, LocalDateTime endDate, SessionPaymentType paymentType, SessionProgressStatus progressStatus, SessionRecruitmentStatus recruitmentStatus, int maximumCapacity) {
        this.id = id;
        this.courseId = courseId;
        this.sessionPeriod = new SessionPeriod(startDate, endDate);
        this.paymentType = paymentType;
        this.progressStatus = progressStatus;
        this.recruitmentStatus = recruitmentStatus;
        this.sessionStudents = new SessionStudents(maximumCapacity);
        this.baseTime = new BaseTime();
    }

    public SessionStudent enrollStudent(NsUser nsUser) {
        if (!progressStatus.isRecruitable()) {
            throw new IllegalArgumentException("해당 강의는 종료되었습니다.");
        }
        if (!recruitmentStatus.isRecruitable()) {
            throw new IllegalArgumentException("해당 강의는 모집 중이 아닙니다.");
        }
        SessionStudent student = new SessionStudent(id, this.id, nsUser.getId(), SessionStudentStatus.REQUEST);
        sessionStudents.enrollStudent(student);
        return student;
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

    public SessionPaymentType getPaymentType() {
        return paymentType;
    }

    public SessionProgressStatus getProgressStatus() {
        return progressStatus;
    }

    public SessionRecruitmentStatus getRecruitmentStatus() {
        return recruitmentStatus;
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
                ", paymentType=" + paymentType +
                ", progressStatus=" + progressStatus +
                ", recruitmentStatus=" + recruitmentStatus +
                ", sessionStudents=" + sessionStudents +
                ", sessionCoverImage=" + sessionCoverImage +
                ", baseTime=" + baseTime +
                ", course=" + course +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return Objects.equals(id, session.id) && Objects.equals(courseId, session.courseId) && Objects.equals(sessionPeriod, session.sessionPeriod) && paymentType == session.paymentType && progressStatus == session.progressStatus && recruitmentStatus == session.recruitmentStatus && Objects.equals(sessionStudents, session.sessionStudents) && Objects.equals(sessionCoverImage, session.sessionCoverImage) && Objects.equals(baseTime, session.baseTime) && Objects.equals(course, session.course);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, courseId, sessionPeriod, paymentType, progressStatus, recruitmentStatus, sessionStudents, sessionCoverImage, baseTime, course);
    }
}
