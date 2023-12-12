package nextstep.qna.domain;

import java.util.List;

public interface AnswerRepository {
    List<Answer> findByQuestion(Long questionId);

    void saveAnswer(Answer newAnswer);
}
