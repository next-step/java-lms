package nextstep.courses;

public class CannotRegisterSessionException extends Exception {

    private static final long serialVersionUID = 1L;

    public CannotRegisterSessionException(String message) {
        super(message);
    }

}
