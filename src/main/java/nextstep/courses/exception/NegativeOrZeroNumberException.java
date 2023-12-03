package nextstep.courses.exception;

public class NegativeOrZeroNumberException extends IllegalArgumentException {

    private static final String MESSAGE = ExceptionMessage.NEGATIVE_OR_ZERO_NUMBER.getMessage();

    public NegativeOrZeroNumberException() {
        this(MESSAGE);
    }

    public NegativeOrZeroNumberException(String s) {
        super(s);
    }
}
