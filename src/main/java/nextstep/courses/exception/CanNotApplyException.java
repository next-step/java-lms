package nextstep.courses.exception;

public class CanNotApplyException extends IllegalArgumentException {

    private static final String MESSAGE = ExceptionMessage.CAN_NOT_APPLY.getMessage();

    public CanNotApplyException() {
        this(MESSAGE);
    }

    public CanNotApplyException(String s) {
        super(s);
    }
}
