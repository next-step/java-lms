package nextstep.courses.exception;

import static nextstep.courses.exception.ExceptionMessage.*;

public class NotValidRatioException extends IllegalArgumentException {

    private static final String MESSAGE = NOT_VALID_RATIO.getMessage();

    public NotValidRatioException() {
        this(MESSAGE);
    }

    public NotValidRatioException(double ratio) {
        this(MESSAGE + DELIMITER + ratio);
    }

    public NotValidRatioException(String s) {
        super(s);
    }
}
