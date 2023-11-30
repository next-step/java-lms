package nextstep.qna;

public class CannotDeleteException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public CannotDeleteException(String message) {
        super(message);
    }

    public CannotDeleteException(String message, Throwable cause) {
        super(message, cause);
    }
}
