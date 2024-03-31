package nextstep.qna.infrastructure;

import java.util.Optional;
import nextstep.qna.domain.Question;
import nextstep.qna.domain.QuestionRepository;
import org.springframework.stereotype.Repository;

@Repository("questionRepository")
public class JdbcQuestionRepository implements QuestionRepository {
    @Override
    public Optional<Question> findById(Long id) {
        return Optional.empty();
    }
}
