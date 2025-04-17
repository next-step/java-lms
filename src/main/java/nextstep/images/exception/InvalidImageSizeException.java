package nextstep.images.exception;

public class InvalidImageSizeException extends RuntimeException {

    public InvalidImageSizeException() {
        super("Invalid image size");
    }

    public InvalidImageSizeException(String message) {
        super(message);
    }

    public InvalidImageSizeException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidImageSizeException(Throwable cause) {
        super(cause);
    }
}
