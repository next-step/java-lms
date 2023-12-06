package nextstep.courses.exception;

public class ImageFormatException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ImageFormatException(String message) {
        super(message);
    }
}
