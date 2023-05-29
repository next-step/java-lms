package nextstep.qna.exception;

import nextstep.qna.CannotDeleteException;

public class QnAException extends CannotDeleteException {

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
