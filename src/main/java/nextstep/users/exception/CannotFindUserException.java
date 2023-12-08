package nextstep.users.exception;

public class CannotFindUserException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public CannotFindUserException(String message) {
        super(message);
    }
}
