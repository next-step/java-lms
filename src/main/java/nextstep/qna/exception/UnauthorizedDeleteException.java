package nextstep.qna.exception;

public class UnauthorizedDeleteException extends IllegalArgumentException {
    public UnauthorizedDeleteException() {
        super("질문을 삭제할 권한이 없습니다");
    }
}
