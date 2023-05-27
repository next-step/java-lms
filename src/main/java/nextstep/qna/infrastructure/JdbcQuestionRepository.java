package nextstep.qna.infrastructure;

import nextstep.common.domain.Image;
import nextstep.qna.domain.Question;
import nextstep.qna.domain.QuestionId;
import nextstep.qna.domain.QuestionRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository("questionRepository")
public class JdbcQuestionRepository implements QuestionRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcQuestionRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
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
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("question").usingGeneratedKeyColumns("question_id");

        Map<String, Object> params = new HashMap<>() {{
            put("question_id", Optional.ofNullable(question.getQuestionId().value()).orElseGet(null));
            put("title", question.getTitle());
            put("contents", question.getContents());
            put("deleted", question.isDeleted());
            put("updated_at", question.getUpdatedDate());
            put("created_at", question.getCreatedDate());

        }};

        //put("writer_id", question.getWriter().getUserId().value());
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(params));

        return new Question(
                new QuestionId(key.longValue()),
                question.getTitle(),
                question.getContents(),
                question.getWriter(),
                question.isDeleted(),
                question.getAnswers(),
                question.getUpdatedDate(),
                question.getCreatedDate()
        );
    }

    @Override
    public Optional<Question> findById(long questionId) {
        return Optional.ofNullable(
                jdbcTemplate.queryForObject(
                        "SELECT * FROM questions WHERE id = ?",
                        rowMapper(),
                        questionId)
        );
    }

    private RowMapper<Question> rowMapper() {
        return (resultSet, rowNumber) -> {
//            NsUser writer = retrieveNsUser(resultSet.getLong("writer_id")); // Assuming writer_id is the column name for the writer's ID in the result set
            return new Question(
                    new QuestionId(resultSet.getLong("question_id")),
                    resultSet.getString("contents"),
                    resultSet.getString("title"),
                    null,
                    resultSet.getBoolean("deleted"),
                    new ArrayList<>(),
                    resultSet.getTimestamp("created_at").toLocalDateTime(),
                    //resultSet.getTimestamp("updated_at").toLocalDateTime()
                    LocalDateTime.now()
            );
        };
    }
}
