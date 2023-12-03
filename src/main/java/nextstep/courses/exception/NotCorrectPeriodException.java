package nextstep.courses.exception;

public class NotCorrectPeriodException extends IllegalArgumentException {

    private static final String MESSAGE = ExceptionMessage.NOT_CORRECT_PERIOD.getMessage();

    public NotCorrectPeriodException() {
        this(MESSAGE);
    }

    public NotCorrectPeriodException(String s) {
        super(s);
    }
}
