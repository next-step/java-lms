package nextstep.qna.exception;

public class QuestionDeleteAnswerExistedException extends IllegalArgumentException {
    public QuestionDeleteAnswerExistedException() {
        super("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }
}
