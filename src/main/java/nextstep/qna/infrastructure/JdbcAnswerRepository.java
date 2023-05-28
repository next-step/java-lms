package nextstep.qna.infrastructure;

import nextstep.qna.domain.Answer;
import nextstep.qna.domain.AnswerRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository("answerRepository")
public class JdbcAnswerRepository implements AnswerRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcAnswerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Answer> findByQuestion(Long questionId) {
        throw new RuntimeException();
    }

    @Override
    public Answer save(Answer answer) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("answer").usingGeneratedKeyColumns("answer_id");
        Map<String, Object> params = new HashMap<>() {
            {
                put("answer_id", Optional.ofNullable(answer.getQuestionId().value()).orElseGet(null));
                put("contents", answer.getContent());
                put("deleted", answer.getDeleted());
                put("question_id", Optional.ofNullable(answer.getQuestionId().value()).orElseGet(null));
                put("writer", answer.getWriter().value());
                put("updated_at", answer.getUpdatedAt());
                put("created_at", answer.getCreatedAt());

            }
        };

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(params));
        return new Answer(key.longValue(),
                answer.getWriter(),
                answer.getQuestionId(),
                answer.getContents(),
                answer.getDeleted(),
                answer.getUpdatedAt(),
                answer.getCreatedAt()
        );
    }

}

