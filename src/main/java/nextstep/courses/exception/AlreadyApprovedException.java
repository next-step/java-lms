package nextstep.courses.exception;

import static nextstep.courses.exception.ExceptionMessage.*;

public class AlreadyApprovedException extends IllegalArgumentException {

    private static final String MESSAGE = ALREADY_APPROVED.getMessage();

    public AlreadyApprovedException() {
        this(MESSAGE);
    }

    public AlreadyApprovedException(String s) {
        super(s);
    }
}
