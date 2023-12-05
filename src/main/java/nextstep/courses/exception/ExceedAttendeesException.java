package nextstep.courses.exception;

import static nextstep.courses.exception.ExceptionMessage.*;

public class ExceedAttendeesException extends IllegalArgumentException {

    private static final String MESSAGE = EXCEED_ATTENDEES.getMessage();

    public ExceedAttendeesException() {
        this(MESSAGE);
    }

    public ExceedAttendeesException(int currentAttendees) {
        this(MESSAGE + DELIMITER + currentAttendees);
    }

    public ExceedAttendeesException(String s) {
        super(s);
    }
}
