package qna.domain;

import java.util.List;
import java.util.Optional;

public interface AnswerRepository {
    List<Answer> findByQuestionAndDeletedFalse(Question question);

    Optional<Answer> findByIdAndDeletedFalse(Long id);
}
