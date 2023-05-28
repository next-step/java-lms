package nextstep.qna.infrastructure;

import nextstep.qna.domain.Question;
import nextstep.qna.domain.QuestionId;
import nextstep.qna.domain.QuestionRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
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
        //return saveV1(question);
        //return saveV2(question);
        return saveV3(question);
    }

    public Question saveV3(Question question) {
        String sql = "INSERT INTO question (" +
                "title," +
                "contents," +
                "deleted," +
                "created_at," +
                "updated_at) " +
                "values (?,?,?,?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update((PreparedStatementCreator) connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"QUESTION_ID"});
            ps.setString(1, question.getTitle());
            ps.setString(2, question.getContents());
            ps.setBoolean(3, question.getDeleted());
            ps.setTimestamp(4, Timestamp.valueOf(question.getCreatedDate()));
            ps.setTimestamp(5, Timestamp.valueOf(question.getUpdatedDate()));
            return ps;
        }, keyHolder);

        long savedQuestionId = keyHolder.getKey().longValue();

        QuestionId questionId = new QuestionId(savedQuestionId);
        //Question savedQuestion = Question.of(questionId, question.getTitle(), question.getContents(), question.getWriter(), question.getDeleted(), question.getAnswers(), question.getUpdatedDate(), question.getCreatedDate());

        return Question.of(questionId, question);
    }

    public Question saveV2(Question question) {
        String sql = "INSERT INTO question (" +
                //"writer_id," +
                "title," +
                "contents," +
                "deleted," +
                "created_at," +
                "updated_at) " +
                "values (?,?,?,?,?)";
        int savedQuestionId = jdbcTemplate.update(
                sql,
                //question.getWriter().getUserId(),
                question.getTitle(),
                question.getContents(),
                question.getDeleted(),
                question.getCreatedDate(),
                question.getUpdatedDate()
        );
        return Question.of(
                new QuestionId((long) savedQuestionId),
                question
        );
    }

    public Question saveV1(Question question) {
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
