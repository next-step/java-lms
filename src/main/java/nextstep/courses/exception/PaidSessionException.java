package nextstep.courses.exception;

public class PaidSessionException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public PaidSessionException(String message) {
        super(message);
    }
}
