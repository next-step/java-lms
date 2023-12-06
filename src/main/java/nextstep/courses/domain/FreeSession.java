package nextstep.courses.domain;

import static nextstep.courses.domain.SessionState.READY;

public class FreeSession extends Session {
    private FreeSession(SessionState state, RegisteredUsers registeredUsers, SessionImage coverImage, SessionDuration duration) {
        super(state, registeredUsers, coverImage, duration);
    }

    public FreeSession(SessionDuration duration, SessionImage coverImage) {
        this(READY, new RegisteredUsers(), coverImage, duration);
    }
}
