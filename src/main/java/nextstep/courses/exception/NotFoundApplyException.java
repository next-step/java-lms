package nextstep.courses.exception;

public class NotFoundApplyException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NotFoundApplyException(String message) {
        super(message);
    }
}
