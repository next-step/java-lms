package nextstep.qna.exception.unchecked;

public class WrongRequestException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public WrongRequestException(String message) {
        super(message);
    }
}
