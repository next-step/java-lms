package nextstep.courses.exception;

public class SessionNotOpenException extends RuntimeException{
    public SessionNotOpenException() {
    }

    public SessionNotOpenException(String message) {
        super(message);
    }
}
