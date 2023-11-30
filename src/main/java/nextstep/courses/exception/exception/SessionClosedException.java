package nextstep.courses.exception.exception;

public class SessionClosedException extends IllegalArgumentException {
    private static final long serialVersionUID = 1L;

    public SessionClosedException(String message) {
        super(message);
    }
}
