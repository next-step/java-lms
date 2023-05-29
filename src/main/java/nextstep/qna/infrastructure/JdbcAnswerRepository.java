package nextstep.qna.infrastructure;

import nextstep.qna.domain.Answer;
import nextstep.qna.domain.AnswerRepository;
import nextstep.qna.domain.QuestionId;
import nextstep.users.domain.UserCode;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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
    public List<Answer> findAllByQuestion(Long questionId) {
        String sql = "SELECT * from answer where question_id=?";
        return jdbcTemplate.query(sql, rowMapper(), questionId);
    }

    private RowMapper<Answer> rowMapper() {
        return ((rs, rowNum) -> {
            return new Answer(
                    rs.getLong("answer_id"),
                    new UserCode(rs.getString("writer_user_code")),
                    new QuestionId(rs.getLong("question_id")),
                    rs.getString("contents"),
                    rs.getBoolean("deleted"),
                    rs.getTimestamp("").toLocalDateTime(),
                    rs.getTimestamp("").toLocalDateTime()
            );
        });


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
                put("writer_user_code", answer.getWriter().value());
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

