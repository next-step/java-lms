package nextstep.qna.exception;

public class CannotDeleteException extends Exception {
    private static final long serialVersionUID = 1L;

    public CannotDeleteException(CannotDeleteExceptionMessage message) {
        super(message.getMessage());
    }
}
