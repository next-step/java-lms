package nextstep.image.exception;

public class CannotFindImageCapacityTypeException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public CannotFindImageCapacityTypeException(String message) {
        super(message);
    }
}
