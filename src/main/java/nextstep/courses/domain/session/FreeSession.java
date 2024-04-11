package nextstep.courses.domain.session;

import nextstep.courses.domain.session.image.CoverImage;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.Users;

public class FreeSession extends Session {

    public FreeSession(Long sessionId, SessionPeriod sessionPeriod,
                       CoverImage coverImage, SessionStatusEnum sessionStatus, Users users) {
        super(sessionId, sessionPeriod, coverImage, sessionStatus, users);
    }

    @Override
    public void enrollStudent(NsUser user) {
        if (!this.isSessionOpened()) {
            throw new IllegalArgumentException(SESSION_IS_NOT_OPENED);
        }

        this.users.add(user);
    }
}
