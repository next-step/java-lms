package nextstep.courses;

public class CannotEnrollException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private static final String MESSAGE = "the current session is not in the enrolling status";

    public CannotEnrollException() {
        super(MESSAGE);
    }
}
