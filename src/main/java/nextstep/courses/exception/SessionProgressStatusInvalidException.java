package nextstep.courses.exception;

import java.text.MessageFormat;

import static nextstep.courses.exception.CourseExceptionMessage.*;

public class SessionProgressStatusInvalidException extends CourseException {

    public SessionProgressStatusInvalidException(String status) {
        super(INVALID_SESSION_PROGRESS_STATUS, MessageFormat.format("입력된 상태: {0}", status));
    }

}
