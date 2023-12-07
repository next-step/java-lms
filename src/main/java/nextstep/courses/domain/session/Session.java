package nextstep.courses.domain.session;

import nextstep.courses.CannotEnrollStateException;
import nextstep.courses.domain.course.Course;
import nextstep.users.domain.NsUser;

public class Session {

    private Long id;

    private CoverImage coverImg;

    private SessionStatus sessionStatus = SessionStatus.PREPARE;

    private Period period;

    private SessionUsers sessionUsers = new SessionUsers();

    private SessionType sessionType;

    private Course course;

    protected Session(SessionStatus sessionStatus, Integer maxAttendance) {
        this.sessionStatus = sessionStatus;
        this.sessionType = SessionType.notFreeSession(maxAttendance);
    }

    protected Session(SessionStatus sessionStatus) {
        this.sessionStatus = sessionStatus;
        this.sessionType = SessionType.freeSession();
    }

    public Session(CoverImage coverImage, Period period, SessionType sessionType, Course course) {
        this.coverImg = coverImage;
        this.period = period;
        this.sessionType = sessionType;
        this.course = course;
    }

    public static Session notFreeSession(CoverImage coverImg, int maxAttendance, Course course, Period period) {
        if (!period.isAfterCourseCreatedDate(course)) {
            throw new IllegalArgumentException("유효하지 않는 세션 일정입니다.");
        }
        SessionType sessionType = SessionType.notFreeSession(maxAttendance);
        return new Session(coverImg, period, sessionType, course);
    }

    public static Session freeSession(CoverImage coverImg, Course course, Period period) {
        if (!period.isAfterCourseCreatedDate(course)) {
            throw new IllegalArgumentException("유효하지 않는 세션 일정입니다.");
        }
        SessionType sessionType = SessionType.freeSession();
        return new Session(coverImg, period, sessionType, course);
    }

    public void addUser(NsUser nsUser) {
        if (!sessionStatus.equals(SessionStatus.ENROLL)) {
            throw new CannotEnrollStateException("수강 인원 모집중인 강의가 아닙니다.");
        }
        sessionUsers.addUser(nsUser, sessionType);
    }

    public int attendUserCount() {
        return sessionUsers.totalAttendUsersCount();
    }

}
