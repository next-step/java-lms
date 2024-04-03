package nextstep.qna.domain.answer;

public interface AnswerRepository {
    Answers findByQuestion(Long questionId);
}
