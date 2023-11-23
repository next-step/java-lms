package nextstep.courses;

public class IncorrectAmountException extends IllegalArgumentException {
    private static final long serialVersionUID = 1L;

    public IncorrectAmountException(String message) {
        super(message);
    }
}
