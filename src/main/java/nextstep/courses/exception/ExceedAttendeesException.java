package nextstep.courses.exception;

public class ExceedAttendeesException extends IllegalArgumentException {

    private static final String MESSAGE = ExceptionMessage.EXCEED_ATTENDEES.getMessage();

    public ExceedAttendeesException() {
        this(MESSAGE);
    }

    public ExceedAttendeesException(String s) {
        super(s);
    }
}
