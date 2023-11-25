package nextstep.courses;

public class InvalidImageTypeException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public InvalidImageTypeException(String message) {
        super(message);
    }
}
