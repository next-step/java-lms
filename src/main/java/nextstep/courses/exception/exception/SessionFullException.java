package nextstep.courses.exception.exception;

public class SessionFullException extends IllegalArgumentException {
    private static final long serialVersionUID = 1L;

    public SessionFullException(String message) {
        super(message);
    }
}
