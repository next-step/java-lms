package nextstep.courses.exception;

import java.text.MessageFormat;

public class SessionTypeInvalidException extends CourseException {
    public SessionTypeInvalidException(String type) {
        super(CourseExceptionMessage.INVALID_SESSION_TYPE, MessageFormat.format("입력된 유형: {0}", type));
    }
}
