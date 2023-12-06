package nextstep.courses.exception;

public class AlreadyTakingSessionException extends IllegalArgumentException {

    private static final String MESSAGE = ExceptionMessage.ALREADY_TAKING_SESSION.getMessage();

    public AlreadyTakingSessionException() {
        this(MESSAGE);
    }

    public AlreadyTakingSessionException(String s) {
        super(s);
    }
}
