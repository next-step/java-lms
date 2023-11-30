package nextstep.courses.exception;

public class SessionStateException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public SessionStateException(String message) {
        super(message);
    }
}
