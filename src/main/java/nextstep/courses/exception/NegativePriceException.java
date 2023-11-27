package nextstep.courses.exception;

public class NegativePriceException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NegativePriceException(String message) {
        super(message);
    }
}
