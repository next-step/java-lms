package nextstep.sessions.exception;

public class SessionNotExists extends RuntimeException {

    public SessionNotExists() {
        super("Session does not exist");
    }

    public SessionNotExists(String message) {
        super(message);
    }

    public SessionNotExists(String message, Throwable cause) {
        super(message, cause);
    }

    public SessionNotExists(Throwable cause) {
        super(cause);
    }
}
