package nextstep.sessions.domain;

import nextstep.courses.domain.Course;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUsers;

import java.time.LocalDateTime;

public abstract class Session {
    private final Course course;
    private final SessionPeriod sessionPeriod;
    private final SessionImage sessionImage;
    private LocalDateTime createdAt;
    protected SessionStatus sessionStatus;
    protected final NsUsers nsUsers;


    public Session(Course course, SessionPeriod sessionPeriod, SessionImage sessionImage, SessionStatus sessionStatus) {
        this.course = course;
        this.sessionPeriod = sessionPeriod;
        this.sessionImage = sessionImage;
        this.nsUsers = new NsUsers();
        this.sessionStatus = sessionStatus;
        this.createdAt = LocalDateTime.now();
    }

    public abstract void signUp(NsUser nsUser);

    protected void checkSessionStatus() {
        if (!SessionStatus.RECRUITING.equals(this.sessionStatus)) {
            throw new IllegalArgumentException("현재 강의는 수강 모집 중이 아닙니다.");
        }
    }

    public void changeSessionStatusIsClose() {
        this.sessionStatus = SessionStatus.CLOSE;
    }

    public void changeSessionStatusIsPreparing() {
        this.sessionStatus = SessionStatus.PREPARING;
    }

    public void changeSessionStatusIsRecruiting() {
        this.sessionStatus = SessionStatus.RECRUITING;
    }
}
