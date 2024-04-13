package nextstep.courses.domain.session;

import nextstep.courses.domain.session.image.CoverImage;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.Users;

public abstract class Session {
    public static final String SESSION_NOT_OPENED = "강의 신청 가능 기한이 아닙니다.";
    public static final String ENROLLMENT_ALREADY_DONE = "이미 수강신청이 완료된 강의 입니다.";

    private final Long sessionId;
    private final SessionPeriod sessionPeriod;
    private final CoverImage coverImage;
    private final SessionStatusEnum sessionStatus;
    protected final Users users;

    protected Session(Long sessionId, SessionPeriod sessionPeriod,
                   CoverImage coverImage, SessionStatusEnum sessionStatus, Users users) {
        this.sessionId = sessionId;
        this.sessionPeriod = sessionPeriod;
        this.coverImage = coverImage;
        this.sessionStatus = sessionStatus;
        this.users = users;
    }

    protected boolean isSessionOpened() {
        return sessionStatus.isSessionOpened();
    }

    protected Long getSessionId() {
        return sessionId;
    }

    public abstract void enrollStudent(NsUser user);

}
