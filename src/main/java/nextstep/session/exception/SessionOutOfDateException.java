package nextstep.session.exception;

public class SessionOutOfDateException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public SessionOutOfDateException(String message) {
        super(message);
    }
}
