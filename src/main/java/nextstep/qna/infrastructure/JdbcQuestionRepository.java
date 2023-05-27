package nextstep.qna.infrastructure;

import nextstep.qna.domain.Question;
import nextstep.qna.domain.QuestionId;
import nextstep.qna.domain.QuestionRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("questionRepository")
public class JdbcQuestionRepository implements QuestionRepository {
    private final JdbcOperations jdbcTemplate;

    public JdbcQuestionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Question> findByQuestionId(QuestionId questionId) {
        throw new RuntimeException();
    }

    @Override
    public List<Question> findAll() {
        throw new RuntimeException();
    }

    @Override
    public Question save(Question question) {
        throw new RuntimeException();
    }

    @Override
    public Optional<Question> findById(long questionId) {
        return Optional.empty();
    }
}
