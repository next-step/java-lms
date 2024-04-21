package nextstep.sessions.domain;

import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUsers;

import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Session {
    private final Long id;
    private final Long courseId;
    private final SessionPeriod sessionPeriod;
    private final SessionImage sessionImage;
    private LocalDateTime createdAt;
    protected SessionStatus sessionStatus;
    protected final NsUsers nsUsers;


    public Session(Long id, Long courseId, SessionPeriod sessionPeriod, SessionImage sessionImage, SessionStatus sessionStatus) {
        this.id = id;
        this.courseId = courseId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return Objects.equals(id, session.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
