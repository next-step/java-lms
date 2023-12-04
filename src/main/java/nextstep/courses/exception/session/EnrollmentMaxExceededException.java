package nextstep.courses.exception.session;

public class EnrollmentMaxExceededException extends RuntimeException {
    public EnrollmentMaxExceededException(String message) {
        super(message);
    }
}
