package nextstep.courses;

public class CanNotJoinSessionException extends RuntimeException {

    public CanNotJoinSessionException() {
    }

    public CanNotJoinSessionException(String message) {
        super(message);
    }

    public CanNotJoinSessionException(String message, Throwable cause) {
        super(message, cause);
    }

    public CanNotJoinSessionException(Throwable cause) {
        super(cause);
    }
}
