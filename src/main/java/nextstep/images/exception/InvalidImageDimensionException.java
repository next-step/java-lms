package nextstep.images.exception;

public class InvalidImageDimensionException extends RuntimeException {

    public InvalidImageDimensionException() {
        super("Invalid image type");
    }

    public InvalidImageDimensionException(String message) {
        super(message);
    }

    public InvalidImageDimensionException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidImageDimensionException(Throwable cause) {
        super(cause);
    }

}
