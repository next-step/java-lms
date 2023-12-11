package nextstep.courses.domain.session;

import nextstep.courses.CannotEnrollStateException;
import nextstep.courses.domain.coverImage.CoverImage;
import nextstep.courses.domain.sessionuser.SessionUsers;

import java.time.LocalDateTime;

public class Session {

    private Long id;

    private Long courseId;

    private CoverImage coverImg;

    private SessionStatus sessionStatus = SessionStatus.PREPARE;

    private Period period;

    private SessionUsers sessionUsers = new SessionUsers();

    private SessionType sessionType;

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

    public Session(CoverImage coverImage, Period period, SessionType sessionType) {
        this.coverImg = coverImage;
        this.period = period;
        this.sessionType = sessionType;
    }

    public Session(Long id, SessionStatus status, LocalDateTime startDateTime, LocalDateTime endDateTime,
                   boolean free, Integer maxAttendance) {
        this.id = id;
        this.sessionStatus = status;
        this.period = new Period(startDateTime, endDateTime);
        this.sessionType = free ? SessionType.freeSession() : SessionType.notFreeSession(maxAttendance);
    }

    public static Session notFreeSession(CoverImage coverImg, int maxAttendance, Period period) {
        SessionType sessionType = SessionType.notFreeSession(maxAttendance);
        return new Session(coverImg, period, sessionType);
    }

    public static Session freeSession(CoverImage coverImg, Period period) {
        SessionType sessionType = SessionType.freeSession();
        return new Session(coverImg, period, sessionType);
    }

    public boolean canRegisterNewUser(int currentUserSize) {
        if (!sessionStatus.equals(SessionStatus.ENROLL)) {
            throw new CannotEnrollStateException("수강 인원 모집중인 강의가 아닙니다.");
        }

        return sessionType.canRegisterNewUser(currentUserSize);
    }

    public boolean isAfterCourseWasCreated(LocalDateTime createdAt) {
        return period.isAfter(createdAt);
    }

    public LocalDateTime startDate() {
        return period.getStartDateTime();
    }

    public LocalDateTime endDate() {
        return period.getEndDateTime();
    }

    public boolean isFree() {
        return sessionType.isFree();
    }

    public Integer maxAttendance() {
        return sessionType.getMaxAttendance();
    }

    public String sessionStatus() {
        return sessionStatus.name();
    }

    public Long coverImageId() {
        return coverImg.getId();
    }

    public Long getId() {
        return id;
    }

    public Long courseId() {
        return courseId;
    }
}
