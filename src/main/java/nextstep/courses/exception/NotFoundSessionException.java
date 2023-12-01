package nextstep.courses.exception;

public class NotFoundSessionException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NotFoundSessionException(String message) {
        super(message);
    }
}
