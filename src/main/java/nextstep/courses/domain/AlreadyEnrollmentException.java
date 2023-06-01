package nextstep.courses.domain;

public class AlreadyEnrollmentException extends RuntimeException{

    public AlreadyEnrollmentException(String message) {
        super(message);
    }
}
