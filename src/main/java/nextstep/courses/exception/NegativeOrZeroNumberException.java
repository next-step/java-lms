package nextstep.courses.exception;

import static nextstep.courses.exception.ExceptionMessage.*;

public class NegativeOrZeroNumberException extends IllegalArgumentException {

    private static final String MESSAGE = NEGATIVE_OR_ZERO_NUMBER.getMessage();

    public NegativeOrZeroNumberException() {
    }

    public <T> NegativeOrZeroNumberException(T value) {
        this(MESSAGE + DELIMITER + value);
    }

    public NegativeOrZeroNumberException(String s) {
        super(s);
    }
}
