package nextstep.courses.domain.session;

import nextstep.courses.constant.SessionStatus;
import nextstep.courses.constant.SessionType;
import nextstep.courses.domain.SessionImage;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class FreeSession extends Session {
    public FreeSession(Long sessionId, Long courseId, SessionImage sessionImage, LocalDateTime startTime, LocalDateTime endTime, SessionStatus sessionStatus) {
        super(sessionId, courseId, sessionImage, startTime, endTime, sessionStatus, SessionType.FREE);
    }

    @Override
    public void enroll(NsUser nsUser) {
        validateSession(nsUser);

        super.enrollStudent(nsUser);
    }
}
