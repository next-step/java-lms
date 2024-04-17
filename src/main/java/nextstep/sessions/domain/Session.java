package nextstep.sessions.domain;

import nextstep.courses.domain.Course;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUsers;

public abstract class Session {
    private final Course course;
    private final SessionPeriod sessionPeriod;
    private final SessionImage sessionImage;
    private SessionStatus sessionStatus;
    protected final NsUsers nsUsers;

    public Session(Course course, SessionPeriod sessionPeriod, SessionImage sessionImage, NsUsers nsUsers, SessionStatus sessionStatus) {
        this.course = course;
        this.sessionPeriod = sessionPeriod;
        this.sessionImage = sessionImage;
        this.nsUsers = nsUsers;
        this.sessionStatus = sessionStatus;
    }

    public abstract void signUp(NsUser nsUser);

    protected void checkSessionStatus() {
        if (!SessionStatus.RECRUITING.equals(this.sessionStatus)) {
            throw new IllegalArgumentException("현재 강의는 수강 모집 중이 아닙니다.");
        }
    }
}
