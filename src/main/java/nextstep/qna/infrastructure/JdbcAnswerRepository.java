package nextstep.qna.infrastructure;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import nextstep.qna.domain.Answer;
import nextstep.qna.domain.AnswerRepository;
import nextstep.qna.domain.Question;
import nextstep.users.domain.NsUser;

@Repository("answerRepository")
public class JdbcAnswerRepository implements AnswerRepository {

    private JdbcOperations jdbcTemplate;

    public JdbcAnswerRepository(final JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Answer> findByQuestion(Long questionId) {
        final String sql = "select id, writer_id, question_id, contents from answer where question_id = ?";
        final RowMapper<Answer> rowMapper = (rs, rowNum) -> {
            final Long id = rs.getLong(1);
            final Long writerId = rs.getLong(2);
            final Long qId = rs.getLong(3);
            final String contents = rs.getString(4);

            final NsUser writer = getWriterById(writerId);
            final Question question = getQuestionById(qId);

            return new Answer(id, writer, question, contents);
        };
        return jdbcTemplate.query(sql, rowMapper, questionId);
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

    private Question getQuestionById(final Long id) {
        final String sql = "select id, writer_id, title, contents from question where id = ?";
        final RowMapper<Question> rowMapper = (rs, rowNum) -> {
            final Long questionId = rs.getLong(1);
            final Long writerId = rs.getLong(2);
            final String title = rs.getString(3);
            final String contents = rs.getString(4);

            final NsUser writer = getWriterById(writerId);

            return new Question(questionId, writer, title, contents);
        };
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    private LocalDateTime toLocalDateTime(final Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
