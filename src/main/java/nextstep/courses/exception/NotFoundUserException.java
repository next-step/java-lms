package nextstep.courses.exception;

import static nextstep.courses.message.ExceptionMessage.NOT_FOUND_USER_EXCEPTION;

public class NotFoundUserException extends RuntimeException {

    private static final String MESSAGE = NOT_FOUND_USER_EXCEPTION.getMessage();

    public NotFoundUserException() {
        super(MESSAGE);
    }
}
