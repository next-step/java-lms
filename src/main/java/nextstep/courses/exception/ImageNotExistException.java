package nextstep.courses.exception;

public class ImageNotExistException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ImageNotExistException(String message) {
        super(message);
    }
}
