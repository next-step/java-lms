package nextstep.courses.domain;

import nextstep.courses.domain.enums.EnrollmentStatus;
import nextstep.courses.domain.enums.SessionStatus;
import nextstep.courses.domain.enums.SessionType;
import nextstep.courses.exception.InvalidSessionDateTimeException;
import nextstep.courses.exception.SessionEnrollmentException;
import nextstep.users.domain.User;

import java.util.List;
import java.util.Objects;

public class Session extends BaseEntity {
    private Long id;

    private String period;

    private Image coverImage;

    private SessionTime sessionTime;

    private SessionType sessionType;

    private SessionStatus sessionStatus;

    private Enrollment enrollment;

    protected Session() {
    }

    public Session(Long id, String period, Image coverImage, SessionTime sessionTime, SessionType sessionType, SessionStatus sessionStatus, Enrollment enrollment) {
        this.id = id;
        this.period = period;
        this.coverImage = coverImage;
        this.sessionTime = sessionTime;
        this.sessionType = sessionType;
        this.sessionStatus = sessionStatus;
        this.enrollment = enrollment;
    }


    public static Session create(String period, Image coverImage, SessionTime sessionTime,
                                 SessionType sessionType, SessionStatus sessionStatus,
                                 Enrollment enrollment) throws InvalidSessionDateTimeException {
        Session session = new Session();
        session.period = period;
        session.coverImage = coverImage;
        session.sessionTime = sessionTime;
        session.sessionType = sessionType;
        session.sessionStatus = sessionStatus;
        session.enrollment = enrollment;

        return session;
    }

    public void enroll(User user) throws SessionEnrollmentException {
        if (this.enrollment.getEnrollmentStatus() != null) {
            checkEnrollmentStatus();
        }

        this.enrollment.enroll(user);
    }

    public Long getId() {
        return id;
    }

    public int getEnrollmentUserCount() {
        return this.enrollment.getUsers().size();
    }

    public String getPeriod() {
        return period;
    }

    public Image getCoverImage() {
        return coverImage;
    }

    public SessionTime getSessionTime() {
        return sessionTime;
    }

    public SessionType getSessionType() {
        return sessionType;
    }

    public SessionStatus getSessionStatus() {
        return sessionStatus;
    }

    public Enrollment getEnrollment() {
        return enrollment;
    }

    public List<User> getUsers() {
        return this.enrollment.getUsers();
    }

    private void checkSessionStatus() throws SessionEnrollmentException {
        if (!this.sessionStatus.canEnroll()) {
            throw new SessionEnrollmentException(String.format("현재 강의 진행 상태는 '%s'이며, '%s' 상태에서만 수강 신청이 가능합니다.",
                    this.sessionStatus, SessionStatus.RECRUITING));
        }
    }

    private void checkEnrollmentStatus() throws SessionEnrollmentException {
        if (!this.enrollment.getEnrollmentStatus().canEnroll()) {
            throw new SessionEnrollmentException(String.format("현재 강의 모집 상태는 '%s'이며, '%s' 상태에서만 수강 신청이 가능합니다.",
                    this.sessionStatus, EnrollmentStatus.ENROLLING));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return Objects.equals(id, session.id) && Objects.equals(period, session.period) && Objects.equals(coverImage, session.coverImage) && Objects.equals(sessionTime, session.sessionTime) && sessionType == session.sessionType && sessionStatus == session.sessionStatus && Objects.equals(enrollment, session.enrollment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, period, coverImage, sessionTime, sessionType, sessionStatus, enrollment);
    }

    public void approveUser(User user) {
        this.getEnrollment().getUserEnrollments().stream()
                .filter(userEnrollment -> userEnrollment.getUser().equals(user))
                .findFirst()
                .ifPresent(UserEnrollment::approved);
    }

    public void disApproveUser(User user) {
        this.getEnrollment().getUserEnrollments().stream()
                .filter(userEnrollment -> userEnrollment.getUser().equals(user))
                .findFirst()
                .ifPresent(UserEnrollment::disApproved);
    }
}
