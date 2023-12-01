package nextstep.courses.exception;

public class InvalidSessionStatusException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public InvalidSessionStatusException(String message) {
        super(message);
    }
}
