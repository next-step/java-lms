package nextstep.courses.domain.exception;

public class CannotEnrollException extends RuntimeException {

    public CannotEnrollException(String message) {
        super(message);
    }
}
