package nextstep.qna.infrastructure;

import nextstep.qna.domain.question.Question;
import nextstep.qna.domain.question.QuestionRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("questionRepository")
public class JdbcQuestionRepository implements QuestionRepository {
    @Override
    public Optional<Question> findById(Long id) {
        return Optional.empty();
    }
}
