package nextstep.courses;

public class MaxStudentsNumberExceededException extends Exception {
    private static final long serialVersionUID = 1L;

    public MaxStudentsNumberExceededException(String message) {
        super(message);
    }
}
