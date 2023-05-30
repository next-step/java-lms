package nextstep.qna.infrastructure;

import nextstep.qna.domain.Question;
import nextstep.qna.domain.QuestionId;
import nextstep.qna.domain.QuestionRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("questionRepository")
public class JdbcQuestionRepository implements QuestionRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcQuestionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Question> findByQuestionId(QuestionId questionId) {
        return Optional.ofNullable(jdbcTemplate
                .queryForObject(
                        "SELECT * FROM question WHERE question_id = ?",
                        rowMapper(),
                        questionId.value()
                )
        );
    }

    @Override
    public List<Question> findAll() {
        return jdbcTemplate.query("SELECT * FROM question", rowMapper());
    }

    public Question save(Question question) {
        String sql = "INSERT INTO question " +
                "(writer_user_code," +
                "title," +
                "contents," +
                "deleted," +
                "created_at," +
                "updated_at) " +
                "values (?,?,?,?,?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"QUESTION_ID"});
            ps.setString(1, question.getWriter().value());
            ps.setString(2, question.getTitle());
            ps.setString(3, question.getContents());
            ps.setBoolean(4, question.getDeleted());
            ps.setTimestamp(5, Timestamp.valueOf(question.getCreatedDate()));
            ps.setTimestamp(6, Timestamp.valueOf(question.getUpdatedDate()));
            return ps;
        }, keyHolder);

        long savedQuestionId = keyHolder.getKey().longValue();
        QuestionId questionId = new QuestionId(savedQuestionId);
        return Question.of(questionId, question);
    }

    @Override
    public Optional<Question> findByQuestionId(long questionId) {
        return Optional.ofNullable(
                jdbcTemplate.queryForObject(
                        "SELECT * FROM questions WHERE id = ?",
                        rowMapper(),
                        questionId)
        );
    }

    private RowMapper<Question> rowMapper() {
        return (resultSet, rowNumber) -> {
            return new Question(
                    new QuestionId(resultSet.getLong("question_id")),
                    resultSet.getString("contents"),
                    resultSet.getString("title"),
                    null,
                    resultSet.getBoolean("deleted"),
                    new ArrayList<>(),
                    resultSet.getTimestamp("created_at").toLocalDateTime(),
                    resultSet.getTimestamp("updated_at").toLocalDateTime()
            );
        };
    }
}
