package nextstep.courses.exception;

public class AlreadyAddStudentException extends IllegalArgumentException {
    private static final long serialVersionUID = 1L;

    public AlreadyAddStudentException(String message) {
        super(message);
    }
}
