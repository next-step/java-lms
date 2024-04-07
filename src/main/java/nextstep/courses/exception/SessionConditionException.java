package nextstep.courses.exception;

import java.text.MessageFormat;

public class SessionConditionException extends Exception {

    public SessionConditionException(SessionExceptionMessage exceptionMessage, String detailMessage) {
        super(MessageFormat.format("{0} ({1})", exceptionMessage.getMessage(), detailMessage));
    }

}
