package nextstep.courses.exception;

import static nextstep.courses.exception.ExceptionMessage.*;

public class NotValidHeightException extends IllegalArgumentException {

    private static final String MESSAGE = NOT_VALID_HEIGHT.getMessage();

    public NotValidHeightException() {
        this(MESSAGE);
    }

    public NotValidHeightException(double height) {
        this(MESSAGE + DELIMITER + height);
    }

    public NotValidHeightException(String message) {
        super(message);
    }
}
