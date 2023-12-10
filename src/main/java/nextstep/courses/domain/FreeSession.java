package nextstep.courses.domain;

import nextstep.courses.enumeration.SessionStatus;

public class FreeSession extends Session {

    public FreeSession(Long sessionId, String title, Course course, SessionStatus sessionStatus) {
        super(sessionId, title, course, sessionStatus);
    }

}
