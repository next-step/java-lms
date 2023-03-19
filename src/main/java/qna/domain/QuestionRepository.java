package qna.domain;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository {
    List<Question> findByDeletedFalse();

    Optional<Question> findByIdAndDeletedFalse(Long id);
}
