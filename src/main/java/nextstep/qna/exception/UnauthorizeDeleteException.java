package nextstep.qna.exception;

public class UnauthorizeDeleteException extends Exception {
    public UnauthorizeDeleteException() {
        super("질문을 삭제할 권한이 없습니다");
    }
}
