package nextstep.courses.domain.session;

import nextstep.courses.CannotEnrollStateException;
import nextstep.courses.domain.course.Course;
import nextstep.users.domain.NsUser;

public class Session {

    private Long id;

    private CoverImage coverImg;

    private SessionStatus sessionStatus;

    private Period period;

    private SessionUsers sessionUsers;

    private Course course;

    public Session() {}

    public Session(CoverImage coverImg, boolean isFree, SessionStatus sessionStatus, Integer maxAttendance, Course course, Period period) {
        this.coverImg = coverImg;
        this.sessionStatus = sessionStatus;
        this.sessionUsers = new SessionUsers(isFree, maxAttendance);
        this.course = course;
        this.period = period;
    }

    public Session(CoverImage coverImg, boolean isFree, SessionStatus sessionStatus, Course course, Period period) {
        this(coverImg, isFree, sessionStatus, null, course, period);
    }

    public static Session notFreeSession(CoverImage coverImg, int maxAttendance, Course course, Period period) {
        if (!period.isAfterCourseCreatedDate(course)) {
            throw new IllegalArgumentException("유효하지 않는 세션 일정입니다.");
        }
        return new Session(coverImg,false, SessionStatus.PREPARE, maxAttendance, course, period);
    }

    public static Session freeSession(CoverImage coverImg, Course course, Period period) {
        if (!period.isAfterCourseCreatedDate(course)) {
            throw new IllegalArgumentException("유효하지 않는 세션 일정입니다.");
        }
        return new Session(coverImg, true, SessionStatus.PREPARE, course, period);
    }

    public void addUser(NsUser nsUser) {
        if (!sessionStatus.equals(SessionStatus.ENROLL)) {
            throw new CannotEnrollStateException("수강 인원 모집중인 강의가 아닙니다.");
        }
        sessionUsers.addUser(nsUser);
    }

    public int attendUserCount() {
        return sessionUsers.totalAttendUsersCount();
    }

}
