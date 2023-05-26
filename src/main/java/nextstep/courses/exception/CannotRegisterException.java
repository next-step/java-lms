package nextstep.courses.exception;

public class CannotRegisterException extends RuntimeException {
    public CannotRegisterException(String message) {
        super(message);
    }
}
