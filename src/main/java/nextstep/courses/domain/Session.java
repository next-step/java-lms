package nextstep.courses.domain;

import nextstep.courses.domain.enums.SessionStatus;
import nextstep.courses.domain.enums.SessionType;
import nextstep.courses.exception.InvalidSessionDateTimeException;
import nextstep.courses.exception.SessionEnrollmentException;
import nextstep.users.domain.User;

import java.util.List;
import java.util.Optional;

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
        checkSessionStatus();

        this.enrollment.enroll(user);
    }

    public Long getId() {
        return id;
    }

    public int getEnrollmentUserCount() {
        return this.enrollment.getUsers().size();
    }

    public User getLatestEnrollmentUser(){
        return Optional.ofNullable(enrollment)
                .map(Enrollment::getUsers)
                .flatMap(users -> users.stream().reduce((first, second) -> second))
                .orElse(null);
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
            throw new SessionEnrollmentException(String.format("현재 강의 상태는 '%s'이며, '%s' 상태에서만 수강 신청이 가능합니다.",
                    this.sessionStatus, SessionStatus.RECRUITING));
        }
    }

}
