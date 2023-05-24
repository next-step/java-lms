package nextstep.courses.domain;

public class CannotEnrollException extends RuntimeException{
    public CannotEnrollException(String message) {
        super(message);
    }
}
