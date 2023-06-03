package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.SelectionSession;
import nextstep.courses.domain.session.SelectionSessionRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class JdbcSelectionSessionRepository implements SelectionSessionRepository {
    private JdbcOperations jdbcOperations;

    public JdbcSelectionSessionRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public int save(SelectionSession session) {
        String sql = "insert into selection_session(session_id, selection_session_id) values(?, ?);";
        return jdbcOperations.update(sql, session.getSessionId(), session.getSelectionSessionId());
    }

    @Override
    public Optional<SelectionSession> findById(long id) {
        String sql = "select id, session_id, selection_session_id from selection_session where id = ?";
        RowMapper<SelectionSession> rowMapper = (rs, rowNum) -> new SelectionSession(
                rs.getLong(1),
                rs.getLong(2),
                rs.getLong(3));
        return Optional.ofNullable(jdbcOperations.queryForObject(sql, rowMapper, id));
    }

    @Override
    public Optional<List<SelectionSession>> findBySessionId(long sessionId) {
        String sql = "select id, session_id, selection_session_id from selection_session where session_id = ?";
        RowMapper<SelectionSession> rowMapper = (rs, rowNum) -> new SelectionSession(
                rs.getLong(1),
                rs.getLong(2),
                rs.getLong(3));
        return Optional.ofNullable(jdbcOperations.query(sql, rowMapper, sessionId));
    }
}
