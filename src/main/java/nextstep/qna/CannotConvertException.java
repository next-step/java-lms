package nextstep.qna;

public class CannotConvertException extends RuntimeException {

    public CannotConvertException() {
    }

    public CannotConvertException(String message) {
        super(message);
    }
}
