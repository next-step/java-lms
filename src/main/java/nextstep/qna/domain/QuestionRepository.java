package nextstep.qna.domain;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository {
    Optional<Question> findByQuestionId(QuestionId questionId);

    List<Question> findAll();

    Question save(Question question);

    Optional<Question> findByQuestionId(long questionId);
}
