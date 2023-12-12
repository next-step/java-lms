package nextstep.courses.exception;

import static nextstep.courses.message.ExceptionMessage.ALREADY_ACCEPTED_USER_EXCEPTION;

public class AlreadyAcceptedUserException extends RuntimeException {

    private static final String MESSAGE = ALREADY_ACCEPTED_USER_EXCEPTION.getMessage();

    public AlreadyAcceptedUserException() {
        super(MESSAGE);
    }
}
