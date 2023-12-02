package nextstep.qna.domain;

import nextstep.qna.infrastructure.entity.QuestionEntity;

import java.util.Optional;

public interface QuestionRepository {
    Optional<Question> findById(Long id);
}
