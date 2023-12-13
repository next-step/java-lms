package nextstep.courses.exception;

import static nextstep.courses.exception.ExceptionMessage.*;

public class AlreadyNotApprovedException extends IllegalArgumentException {

    private static final String MESSAGE = ALREADY_NOT_APPROVED.getMessage();

    public AlreadyNotApprovedException() {
        this(MESSAGE);
    }

    public AlreadyNotApprovedException(String s) {
        super(s);
    }
}
