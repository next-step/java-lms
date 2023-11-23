package nextstep.courses;

public class SessionDeadLineException extends IllegalArgumentException {
    private static final long serialVersionUID = 1L;

    public SessionDeadLineException(String message) {
        super(message);
    }
}
