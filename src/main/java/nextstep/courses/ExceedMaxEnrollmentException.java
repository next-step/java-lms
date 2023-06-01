package nextstep.courses;

public class ExceedMaxEnrollmentException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private static final String message = "can not exceed the maximum enrollment";

    public ExceedMaxEnrollmentException() {
        super(message);
    }
}
