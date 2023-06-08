package nextstep.courses.exception;

public class InvalidURLException extends RuntimeException {
    public InvalidURLException(String message) {
        super(message);
    }

    public InvalidURLException(Throwable cause) {
        super(cause);
    }
}
