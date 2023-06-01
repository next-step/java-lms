package nextstep.courses.exception;

public class EnrollFullException extends RuntimeException{
    public EnrollFullException() {
    }

    public EnrollFullException(String message) {
        super(message);
    }
}
