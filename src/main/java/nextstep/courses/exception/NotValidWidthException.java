package nextstep.courses.exception;

import static nextstep.courses.exception.ExceptionMessage.*;

public class NotValidWidthException extends IllegalArgumentException {

    private static final String MESSAGE = NOT_VALID_WIDTH.getMessage();

    public NotValidWidthException() {
        this(MESSAGE);
    }

    public NotValidWidthException(double width) {
        this(MESSAGE + DELIMITER + width);
    }

    public NotValidWidthException(String message) {
        super(message);
    }
}
