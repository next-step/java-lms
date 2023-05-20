package nextstep.qna.exception;

public class QuestionDeleteUnauthorizedException extends IllegalArgumentException {
    public QuestionDeleteUnauthorizedException() {
        super("질문을 삭제할 권한이 없습니다");
    }
}
