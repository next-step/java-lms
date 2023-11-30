package nextstep.courses.exception.exception;

public class NotExtensionTypeException extends IllegalArgumentException {
    private static final long serialVersionUID = 1L;

    public NotExtensionTypeException(String message) {
        super(message);
    }
}
