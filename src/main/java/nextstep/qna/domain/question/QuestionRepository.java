package nextstep.qna.domain.question;

import java.util.Optional;

public interface QuestionRepository {
    Optional<Question> findById(Long id);
}
