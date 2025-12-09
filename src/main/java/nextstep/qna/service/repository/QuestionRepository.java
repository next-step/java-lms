package nextstep.qna.service.repository;

import java.util.Optional;
import nextstep.qna.domain.Question;

public interface QuestionRepository {
    Optional<Question> findById(Long id);
}
