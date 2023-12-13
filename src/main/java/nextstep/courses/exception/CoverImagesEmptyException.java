package nextstep.courses.exception;

public class CoverImagesEmptyException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public CoverImagesEmptyException(String message) {
        super(message);
    }
}
