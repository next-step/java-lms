package nextstep.qna.repository;

import nextstep.qna.domain.question.Question;

import java.util.Optional;

public interface QuestionRepository {
    Optional<Question> findById(Long id);
}
