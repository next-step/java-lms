package nextstep.courses.exception;

public class NegativeNumberException extends IllegalArgumentException {

    private static final String MESSAGE = ExceptionMessage.NEGATIVE_NUMBER.getMessage();

    public NegativeNumberException() {
        this(MESSAGE);
    }

    public NegativeNumberException(String s) {
        super(s);
    }
}
