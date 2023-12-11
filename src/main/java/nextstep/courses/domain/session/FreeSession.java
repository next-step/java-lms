package nextstep.courses.domain.session;

import nextstep.courses.domain.Course;

public class FreeSession extends Session {

    private final int LIMIT_STUDENT_COUNT = 0;
    private final Long SESSION_FEE = 0L;

    public FreeSession(Long sessionId, String title, Course course) {
        super(sessionId, title, course, SessionType.FREE, SessionStatus.PREPARING);
    }

}
