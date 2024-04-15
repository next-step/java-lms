package nextstep.courses.exception;

import java.text.MessageFormat;

public class SessionStatusInvalidException extends CourseException {

    public SessionStatusInvalidException(String status) {
        super(CourseExceptionMessage.INVALID_SESSION_STATUS, MessageFormat.format("입력된 상태: {0}", status));
    }

}
