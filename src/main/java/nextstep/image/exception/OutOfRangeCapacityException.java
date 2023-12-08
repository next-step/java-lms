package nextstep.image.exception;

public class OutOfRangeCapacityException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public OutOfRangeCapacityException(String message) {
        super(message);
    }
}
