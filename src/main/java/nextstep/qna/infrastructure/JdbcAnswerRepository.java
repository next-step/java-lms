package nextstep.qna.infrastructure;

import nextstep.qna.domain.Answer;
import nextstep.qna.domain.AnswerRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("answerRepository")
public class JdbcAnswerRepository implements AnswerRepository {

    private final JdbcOperations jdbcTemplate;

    public JdbcAnswerRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Answer> findByQuestion(Long questionId) {
        throw new RuntimeException();
    }

    @Override
    public Answer save(Answer answer) {
        throw new RuntimeException();
    }
}
