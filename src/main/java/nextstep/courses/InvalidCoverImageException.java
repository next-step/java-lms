package nextstep.courses;

public class InvalidCoverImageException extends RuntimeException {

    public InvalidCoverImageException() {
    }

    public InvalidCoverImageException(String message) {
        super(message);
    }

    public InvalidCoverImageException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidCoverImageException(Throwable cause) {
        super(cause);
    }
}
