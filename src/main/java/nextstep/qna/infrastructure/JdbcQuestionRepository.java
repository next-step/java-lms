package nextstep.qna.infrastructure;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import nextstep.qna.domain.Question;
import nextstep.qna.domain.QuestionRepository;
import nextstep.users.domain.NsUser;

@Repository("questionRepository")
public class JdbcQuestionRepository implements QuestionRepository {

    private JdbcOperations jdbcTemplate;

    public JdbcQuestionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Question> findById(Long id) {
        final String sql = "select id, writer_id, title, contents from question where id = ?";
        final RowMapper<Question> rowMapper = (rs, rowNum) -> {
            final Long questionId = rs.getLong(1);
            final Long writerId = rs.getLong(2);
            final String title = rs.getString(3);
            final String contents = rs.getString(4);

            final NsUser writer = getWriterById(writerId);

            return new Question(questionId, writer, title, contents);
        };
        return Optional.of(jdbcTemplate.queryForObject(sql, rowMapper, id));
    }

    private NsUser getWriterById(final Long writerId) {
        final String sql = "select id, user_id, password, name, email, created_at, updated_at from ns_user where id = ?";
        final RowMapper<NsUser> rowMapper = (rs, rowNum) -> new NsUser(
                rs.getLong(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5),
                toLocalDateTime(rs.getTimestamp(6)),
                toLocalDateTime(rs.getTimestamp(7)));
        return jdbcTemplate.queryForObject(sql, rowMapper, writerId);
    }

    private LocalDateTime toLocalDateTime(final Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
