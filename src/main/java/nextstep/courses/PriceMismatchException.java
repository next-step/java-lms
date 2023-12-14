package nextstep.courses;

public class PriceMismatchException extends Exception {
    private static final long serialVersionUID = 1L;

    public PriceMismatchException(String message) {
        super(message);
    }
}
