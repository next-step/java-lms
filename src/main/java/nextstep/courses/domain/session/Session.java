package nextstep.courses.domain.session;

import nextstep.courses.CannotEnrollStateException;
import nextstep.courses.domain.course.Course;
import nextstep.users.domain.NsUser;

import java.time.LocalDate;

public class Session {

    private Long id;

    private CoverImg coverImg;

    private SessionStatus sessionStatus;

    private LocalDate startDate;

    private LocalDate endDate;

    private SessionUsers sessionUsers;

    private Course course;

    public Session() {}

    public Session(CoverImg coverImg, boolean isFree, SessionStatus sessionStatus, Integer maxAttendance, Course course) {
        this.coverImg = coverImg;
        this.sessionStatus = sessionStatus;
        this.sessionUsers = new SessionUsers(isFree, maxAttendance);
        this.course = course;
    }

    public Session(CoverImg coverImg, boolean isFree, SessionStatus sessionStatus, Course course) {
        this(coverImg, isFree, sessionStatus, null, course);
    }

    public static Session notFreeSession(CoverImg coverImg, int maxAttendance, Course course) {
        return new Session(coverImg,false, SessionStatus.PREPARE, maxAttendance, course);
    }

    public static Session FreeSession(CoverImg coverImg, Course course) {
        return new Session(coverImg, true, SessionStatus.PREPARE, course);
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
