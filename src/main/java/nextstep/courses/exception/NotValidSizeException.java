package nextstep.courses.exception;

public class NotValidSizeException extends IllegalArgumentException {

    private static final String MESSAGE = ExceptionMessage.NOT_VALID_SIZE.getMessage();

    public NotValidSizeException() {
        this(MESSAGE);
    }

    public NotValidSizeException(String s) {
        super(s);
    }
}
