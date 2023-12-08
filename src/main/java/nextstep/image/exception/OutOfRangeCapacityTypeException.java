package nextstep.image.exception;

public class OutOfRangeCapacityTypeException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public OutOfRangeCapacityTypeException(String message) {
        super(message);
    }
}
