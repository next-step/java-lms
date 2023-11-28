package nextstep.courses.exception;

public class NotFoundStudentException extends IllegalArgumentException {
    private static final long serialVersionUID = 1L;

    public NotFoundStudentException(String message) {
        super(message);
    }
}
