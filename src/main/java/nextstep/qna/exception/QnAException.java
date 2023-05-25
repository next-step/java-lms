package nextstep.qna.exception;

public class QnAException extends RuntimeException{

    protected Enum<?> code;
    public <E extends Enum<E>> QnAException(QnAExceptionCode code) {
        this(code, code.message());
        this.code = code;
    }

    public <E extends Enum<E>> QnAException(Enum<E> code, String message) {
        super(message);
        this.code = code;
    }
}
