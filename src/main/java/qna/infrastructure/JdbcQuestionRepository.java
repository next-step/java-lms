package qna.infrastructure;

import org.springframework.stereotype.Repository;
import qna.domain.Question;
import qna.domain.QuestionRepository;

import java.util.List;
import java.util.Optional;

@Repository("questionRepository")
public class JdbcQuestionRepository implements QuestionRepository {
    @Override
    public Optional<Question> findById(Long id) {
        return Optional.empty();
    }
}
