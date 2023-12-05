package nextstep.image.exception;

public class CannotFindImageTypeException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public CannotFindImageTypeException(String message) {
        super(message);
    }
}
