package nextstep.courses;

public class CannotRegisterSessionException extends RuntimeException {
    public CannotRegisterSessionException(String message) {
        super(message);
    }
}
