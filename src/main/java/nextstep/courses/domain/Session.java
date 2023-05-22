package nextstep.courses.domain;

import nextstep.courses.domain.enums.SessionStatus;
import nextstep.courses.domain.enums.SessionType;
import nextstep.courses.exception.InvalidSessionDateTimeException;
import nextstep.courses.exception.SessionEnrollmentException;
import nextstep.users.domain.User;

public class Session extends BaseEntity {
    private Long id;

    private String period;

    private Image coverImage;

    private SessionTime sessionTime;

    private SessionType sessionType;

    private SessionStatus sessionStatus;

    private Enrollment enrollment;

    private int maximumEnrollment;

    protected Session() {
    }

    public Session(Long id, String period, Image coverImage, SessionTime sessionTime, String sessionType, String sessionStatus, Enrollment enrollment, int maximumEnrollment) {
        this.id = id;
        this.period = period;
        this.coverImage = coverImage;
        this.sessionTime = sessionTime;
        this.sessionType = SessionType.valueOf(sessionType.toUpperCase());
        this.sessionStatus = SessionStatus.valueOf(sessionStatus.toUpperCase());
        this.enrollment = enrollment;
        this.maximumEnrollment = maximumEnrollment;
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
        checkSessionStatus();

        this.enrollment.enroll(user);
    }

    public int getEnrollmentUserCount() {
        return this.enrollment.getUsers().size();
    }

    private void checkSessionStatus() throws SessionEnrollmentException {
        if (!this.sessionStatus.canEnroll()) {
            throw new SessionEnrollmentException(String.format("현재 강의 상태는 '%s'이며, '%s' 상태에서만 수강 신청이 가능합니다.",
                    this.sessionStatus, SessionStatus.RECRUITING));
        }
    }

    public Long getId() {
        return id;
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

    public int getMaximumEnrollment() {
        return maximumEnrollment;
    }
}
