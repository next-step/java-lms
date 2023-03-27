package nextstep.qna.infrastructure;

import org.springframework.stereotype.Repository;
import nextstep.qna.domain.Question;
import nextstep.qna.domain.QuestionRepository;

import java.util.Optional;

@Repository("questionRepository")
public class JdbcQuestionRepository implements QuestionRepository {
    @Override
    public Optional<Question> findById(Long id) {
        return Optional.empty();
    }
}
