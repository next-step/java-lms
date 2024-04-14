package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {

    private final JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Session session) {
        String sql = "insert into session (fee, count, status, course_id, start_date, end_date, created_at, updated_at) values(?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(
                sql,
                session.getEnrollmentManager().getFee(),
                session.getEnrollmentManager().getCount(),
                session.getEnrollmentManager().getStatus(),
                session.getCourseId(),
                session.getSessionPeriod().getStartDate(),
                session.getSessionPeriod().getEndDate(),
                session.getCreatedAt(),
                session.getUpdatedAt()
                );
    }

    @Override
    public Optional<Session> findById(Long sessionId) {
        String sql = "select id, fee, count, status, course_id, start_date, end_date, created_at, updated_at from session where id = ?";
        RowMapper<Session> rowMapper = (rs, rowNum) -> new Session(
                LocalDateMappingUtil.toLocalDateTime(rs.getTimestamp(8)),
                LocalDateMappingUtil.toLocalDateTime(rs.getTimestamp(9)),
                null,
                rs.getLong(2),
                rs.getInt(3),
                rs.getString(4),
                LocalDateMappingUtil.toLocalDate(rs.getTimestamp(6)),
                LocalDateMappingUtil.toLocalDate(rs.getTimestamp(7)),
                rs.getLong(5),
                rs.getLong(1)
                );
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper, sessionId));
    }
}
