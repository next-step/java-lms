package nextstep.courses.infrastructure;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import nextstep.courses.domain.SessionType;
import nextstep.users.domain.NsUser;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {
    private final JdbcOperations jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    public JdbcSessionRepository(JdbcOperations jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public int save(Session session) {
        String sql = "insert into session (id, title, free, created_at, imgFile, started_at, ended_at, status, course_id) values(?, ?, ?, ? ,? ,? ,?, ?, ?)";
        return jdbcTemplate.update(sql,session.getId(), session.getTitle(), session.getFree(), LocalDate.now(), session.getImageFile(),
                session.getSessionDuration().getStartedAt(), session.getSessionDuration().getEndedAt()
                ,session.getStatus().toString(), session.getCourseId());
    }

    @Override
    public Session findById(Long id) {
        String sql = "select id, title, free, created_at, started_at, ended_at, imgFile, status from session where id = ?";
        RowMapper<Session> rowMapper = (rs, rowNum) -> new Session(
                rs.getLong(1),
                rs.getString(2),
                rs.getBoolean(3),
                toLocalDateTime(rs.getTimestamp(4)),
                toLocalDateTime(rs.getTimestamp(5)),
                toLocalDateTime(rs.getTimestamp(6)),
                rs.getString(7),
                SessionType.valueOf(rs.getString(8)));
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public List<Session> findByCourseId(Long courseId) {
        String sql = "select id, title, free, created_at, started_at, ended_at, imgFile, status, course_id from session where course_id = ?";
        RowMapper<Session> rowMapper = (rs, rowNum) -> new Session(
                rs.getLong(1),
                rs.getString(2),
                rs.getBoolean(3),
                toLocalDateTime(rs.getTimestamp(4)),
                toLocalDateTime(rs.getTimestamp(5)),
                toLocalDateTime(rs.getTimestamp(6)),
                rs.getString(7),
                SessionType.valueOf(rs.getString(8)));
        return jdbcTemplate.query(sql, rowMapper, courseId);
    }

    @Override
    public int update(Session session) {
        String sql = "update session set title=?, free=?, status=? where id = ?";
        return jdbcTemplate.update(sql, session.getTitle(), session.getFree(), session.getStatus().toString(), session.getId());
    }

    @Override
    public int delete(Long id) {
        String sql = "delete from session where id = ?";
        return jdbcTemplate.update(sql, id);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }

    @Override
    public Optional<List<Session>> findBySessionIds(List<Long> sessionIds) {
        String sql = "select id, title, free, created_at, started_at, ended_at, imgFile, status, course_id from session where id IN (:values)";

        MapSqlParameterSource parameters = new MapSqlParameterSource("values", sessionIds);
        RowMapper<Session> rowMapper = (rs, rowNum) -> new Session(
                rs.getLong(1),
                rs.getString(2),
                rs.getBoolean(3),
                toLocalDateTime(rs.getTimestamp(4)),
                toLocalDateTime(rs.getTimestamp(5)),
                toLocalDateTime(rs.getTimestamp(6)),
                rs.getString(7),
                SessionType.valueOf(rs.getString(8)));

        return Optional.of(namedParameterJdbcTemplate.query(sql, parameters, rowMapper));
    }
}
