package nextstep.session.infrastructure;

import nextstep.session.domain.student.SessionStudent;
import nextstep.session.domain.student.SessionStudentRepository;
import nextstep.session.domain.student.SessionStudentStatus;
import nextstep.users.domain.NsUser;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository("studentRepository")
public class JdbcSessionStudentRepository implements SessionStudentRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcSessionStudentRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long enrollStudent(Long sessionId, Long nsUserId) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        String sql = "INSERT INTO session_student (session_id, ns_user_id, status) VALUES(?, ?, ?)";

        jdbcTemplate.update(connection -> {
            PreparedStatement pstmt = connection.prepareStatement(sql, new String[]{"id"});
            pstmt.setLong(1, sessionId);
            pstmt.setLong(2, nsUserId);
            pstmt.setString(3, SessionStudentStatus.REQUEST.name());
            return pstmt;
        }, keyHolder);
        return keyHolder.getKeyAs(Long.class);
    }

    @Override
    public Optional<SessionStudent> getStudentById(Long studentId) {
        String sql = "SELECT id, session_id, ns_user_id, status FROM session_student WHERE id = ?";

        RowMapper<SessionStudent> rowMapper = (rs, rowNum) -> new SessionStudent(
                rs.getLong("id"),
                rs.getLong("session_id"),
                rs.getLong("ns_user_id"),
                SessionStudentStatus.of(rs.getString("status"))
        );

        try {
            return Optional.of(jdbcTemplate.queryForObject(sql, rowMapper, studentId));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<SessionStudent> getStudents(Long sessionId) {
        String sql = "SELECT id, session_id, ns_user_id, status FROM session_student WHERE session_id = ?";

        RowMapper<SessionStudent> rowMapper = (rs, rowNum) -> new SessionStudent(
                rs.getLong("id"),
                rs.getLong("session_id"),
                rs.getLong("ns_user_id"),
                SessionStudentStatus.of(rs.getString("status"))
        );

        return jdbcTemplate.query(sql, rowMapper, sessionId);
    }

    @Override
    public Optional<SessionStudent> getStudent(Long sessionId, Long nsUserId) {
        String sql = "SELECT id, session_id, ns_user_id, status FROM session_student WHERE session_id = ? AND ns_user_id = ?";

        RowMapper<SessionStudent> rowMapper = (rs, rowNum) -> new SessionStudent(
                rs.getLong("id"),
                rs.getLong("session_id"),
                rs.getLong("ns_user_id"),
                SessionStudentStatus.of(rs.getString("status"))
        );

        try {
            return Optional.of(jdbcTemplate.queryForObject(sql, rowMapper, sessionId, nsUserId));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<NsUser> findAllUserBySessionId(Long sessionId) {
        String sql = "SELECT U.id, U.user_id, U.password, U.name, U.email, U.created_at, U.updated_at " +
                "FROM ns_user U " +
                "INNER JOIN session_student SS ON U.id = SS.ns_user_id WHERE SS.session_id = ?";
        RowMapper<NsUser> rowMapper = (rs, rowNum) -> new NsUser(
                rs.getLong(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5),
                toLocalDateTime(rs.getTimestamp(6)),
                toLocalDateTime(rs.getTimestamp(7)));
        return jdbcTemplate.query(sql, rowMapper, sessionId);
    }

    @Override
    public int changeStudentStatus(Long studentId, SessionStudentStatus status) {
        String sql = "UPDATE session_student SET status = ?, updated_at = now() WHERE id = ?";
        return jdbcTemplate.update(sql, status.name(), studentId);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
