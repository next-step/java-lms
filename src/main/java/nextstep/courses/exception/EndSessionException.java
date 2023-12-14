package nextstep.courses.exception;

import static nextstep.courses.message.ExceptionMessage.END_SESSION_EXCEPTION;

public class EndSessionException extends RuntimeException {

    private static final String MESSAGE = END_SESSION_EXCEPTION.getMessage();

    public EndSessionException() {
        super(MESSAGE);
    }
}
