package nextstep.courses.exception;

public class CannotStartDateAfterEndDateException extends IllegalArgumentException {
    private static final long serialVersionUID = 1L;

    public CannotStartDateAfterEndDateException(String message) {
        super(message);
    }
}
