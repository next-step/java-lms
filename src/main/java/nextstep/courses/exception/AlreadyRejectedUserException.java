package nextstep.courses.exception;

import static nextstep.courses.message.ExceptionMessage.ALREADY_REJECTED_USER_EXCEPTION;

public class AlreadyRejectedUserException extends RuntimeException {

    private static final String MESSAGE = ALREADY_REJECTED_USER_EXCEPTION.getMessage();

    public AlreadyRejectedUserException() {
        super(MESSAGE);
    }
}
