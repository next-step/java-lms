package nextstep.courses;

public class CannotRegisterException extends Exception {

    private static final long serialVersionUID = 1L;

    public CannotRegisterException(String message) {
        super(message);
    }
}
