package nextstep.qna.exception;

public class QuestionNotFoundException extends RuntimeException {
    public QuestionNotFoundException() {
        super("Question 을 찾을수 없습니다");
    }
}
