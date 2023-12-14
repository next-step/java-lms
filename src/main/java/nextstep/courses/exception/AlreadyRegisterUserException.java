package nextstep.courses.exception;

import static nextstep.courses.message.ExceptionMessage.ALREADY_REGISTER_USER_EXCEPTION;

public class AlreadyRegisterUserException extends RuntimeException {

    private static final String MESSAGE = ALREADY_REGISTER_USER_EXCEPTION.getMessage();

    public AlreadyRegisterUserException() {
        super(MESSAGE);
    }
}
