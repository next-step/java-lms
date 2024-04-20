package nextstep.courses.exception;

import java.text.MessageFormat;

public class SessionNotFoundException extends CourseException {
    public SessionNotFoundException(Long sessionId) {
        super(CourseExceptionMessage.SESSION_NOT_FOUNT,
                MessageFormat.format("입력된 강의 아이디: {0}", sessionId));
    }
}
