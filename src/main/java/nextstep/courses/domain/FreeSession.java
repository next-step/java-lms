package nextstep.courses.domain;

import nextstep.courses.constant.SessionStatus;
import nextstep.courses.constant.SessionType;

import java.time.LocalDateTime;

public class FreeSession extends Session {
    public FreeSession(Course course, SessionImage sessionImage, LocalDateTime startTime, LocalDateTime endTime, SessionStatus sessionStatus, SessionType sessionType) {
        super(course, sessionImage, startTime, endTime, sessionStatus, sessionType);
    }
}
