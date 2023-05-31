package nextstep.courses.domain;

public class SessionMaxStudentsExceedException extends Exception{
    public SessionMaxStudentsExceedException(String message) {
        super(message);
    }
}
