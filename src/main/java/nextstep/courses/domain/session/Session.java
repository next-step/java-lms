package nextstep.courses.domain.session;

import nextstep.courses.CannotEnrollStateException;
import nextstep.courses.domain.course.Course;
import nextstep.courses.domain.coverImage.CoverImage;
import nextstep.courses.domain.sessionuser.SessionUsers;

import java.time.LocalDateTime;

public class Session {

    private Long id;

    private CoverImage coverImg;

    private SessionStatus sessionStatus = SessionStatus.PREPARE;

    private Period period;

    private SessionUsers sessionUsers = new SessionUsers();

    private SessionType sessionType;

    private Course course;

    public Session(Long id) {
        this.id = id;
    }

    public Session(SessionStatus sessionStatus, Integer maxAttendance) {
        this.sessionStatus = sessionStatus;
        this.sessionType = SessionType.notFreeSession(maxAttendance);
    }

    public Session(SessionStatus sessionStatus) {
        this.sessionStatus = sessionStatus;
        this.sessionType = SessionType.freeSession();
    }

    public Session(CoverImage coverImage, Period period, SessionType sessionType, Course course) {
        this.coverImg = coverImage;
        this.period = period;
        this.sessionType = sessionType;
        this.course = course;
    }

    public Session(Long id, SessionStatus status, LocalDateTime startDateTime, LocalDateTime endDateTime,
                   boolean free, Integer maxAttendance) {
        this.id = id;
        this.sessionStatus = status;
        this.period = new Period(startDateTime, endDateTime);
        this.sessionType = free ? SessionType.freeSession() : SessionType.notFreeSession(maxAttendance);
    }

    public static Session notFreeSession(CoverImage coverImg, int maxAttendance, Course course, Period period) {
        SessionType sessionType = SessionType.notFreeSession(maxAttendance);
        return new Session(coverImg, period, sessionType, course);
    }

    public static Session freeSession(CoverImage coverImg, Course course, Period period) {
        SessionType sessionType = SessionType.freeSession();
        return new Session(coverImg, period, sessionType, course);
    }

    public boolean canRegisterNewUser(int currentUserSize) {
        if (!sessionStatus.equals(SessionStatus.ENROLL)) {
            throw new CannotEnrollStateException("수강 인원 모집중인 강의가 아닙니다.");
        }

        return sessionType.canRegisterNewUser(currentUserSize);
    }

    public SessionStatus getSessionStatus() {
        return sessionStatus;
    }

    public Period getPeriod() {
        return period;
    }

    public SessionType getSessionType() {
        return sessionType;
    }

    public Course getCourse() {
        return course;
    }

    public CoverImage getCoverImg() {
        return coverImg;
    }

    public Long getId() {
        return id;
    }
}
