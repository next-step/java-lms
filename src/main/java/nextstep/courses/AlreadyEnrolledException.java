package nextstep.courses;

public class AlreadyEnrolledException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private static final String MESSAGE = "already enrolled";

    public AlreadyEnrolledException() {
        super(MESSAGE);
    }
}
