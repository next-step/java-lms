package nextstep.courses.exception.exception;

public class AlreadyAddStudentException extends IllegalArgumentException {
    private static final long serialVersionUID = 1L;

    public AlreadyAddStudentException(String message) {
        super(message);
    }
}
