package nextstep.courses.domain.exception;

public class AlreadyEnrollmentException extends RuntimeException{
    public AlreadyEnrollmentException(String message) {
        super(message);
    }
}
