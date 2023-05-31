package nextstep.courses.domain;

public class SessionNotOpenedException extends Exception{
    public SessionNotOpenedException(String message) {
        super(message);
    }
}
