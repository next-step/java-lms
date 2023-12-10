package nextstep.courses.domain;

public class FreeSession extends Session {

    public FreeSession(Long sessionId, String title, Course course, SessionStatus sessionStatus) {
        super(sessionId, title, course, sessionStatus);
    }

}
