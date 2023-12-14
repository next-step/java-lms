package nextstep.courses.exception;

import static nextstep.courses.message.ExceptionMessage.*;

public class NotFoundEnrolmentException extends RuntimeException {

    private static final String MESSAGE = NOT_FOUND_ENROLMENT_EXCEPTION.getMessage();

    public NotFoundEnrolmentException() {
        super(MESSAGE);
    }
}
