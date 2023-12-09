package nextstep.sessions.infrastructure;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import nextstep.sessions.domain.data.session.*;
import nextstep.sessions.repository.SessionRepository;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {

    private final JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Session> findById(int sessionId) {
        String sql =
            "    select " +
                "  id, " +
                "  paid_type, " +
                "  fee, " +
                "  capacity, " +
                "  running_state, " +
                "  recruiting_state, " +
                "  start_date, " +
                "  end_date " +
                "from new_session " +
                "where id = ? ";
        RowMapper<Session> rowMapper = (rs, rowNum) -> session(rs);

        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper, sessionId));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public static Session session(ResultSet rs) throws SQLException {
        return new Session(
            rs.getLong(1),
            PaidType.valueOf(rs.getString(2)),
            rs.getLong(3),
            rs.getInt(4),
            SessionRunningState.valueOf(rs.getString(5)),
            SessionRecruitingState.valueOf(rs.getString(6)),
            toLocalDateTime(rs.getTimestamp(7)),
            toLocalDateTime(rs.getTimestamp(8))
        );
    }

    @Override
    public int save(Session session) {
        String sql =
            "    insert into new_session ( " +
                "  paid_type, " +
                "  fee, " +
                "  capacity, " +
                "  running_state, " +
                "  recruiting_state, " +
                "  start_date, " +
                "  end_date " +
                ") values( " +
                "  ?, " +
                "  ?, " +
                "  ?, " +
                "  ?, " +
                "  ?, " +
                "  ?, " +
                "  ?" +
                ") ";

        return jdbcTemplate.update(sql,
            session.paidType(),
            session.fee(),
            session.capacity(),
            session.sessionRunningState(),
            session.sessionRecruitingState(),
            Timestamp.valueOf(session.startDate()),
            Timestamp.valueOf(session.endDate())
        );
    }

    public static LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
