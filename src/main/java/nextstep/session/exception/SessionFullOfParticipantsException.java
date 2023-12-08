package nextstep.session.exception;

public class SessionFullOfParticipantsException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public SessionFullOfParticipantsException(String message) {
        super(message);
    }
}
