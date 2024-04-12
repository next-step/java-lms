package nextstep.courses;

public class CannotEnrollException extends RuntimeException {
    public CannotEnrollException(String message) {
        super(message);
    }
}
