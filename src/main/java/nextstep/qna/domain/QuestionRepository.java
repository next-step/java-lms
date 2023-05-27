package nextstep.qna.domain;

import java.util.Optional;

public interface QuestionRepository {

    Optional<Question> findById(Long id);
}
