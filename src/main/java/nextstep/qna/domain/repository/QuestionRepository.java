package nextstep.qna.domain.repository;

import nextstep.qna.domain.Question;

import java.util.Optional;

public interface QuestionRepository {
    Optional<Question> findById(Long id);
}
