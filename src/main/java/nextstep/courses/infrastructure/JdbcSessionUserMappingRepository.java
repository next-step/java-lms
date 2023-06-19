package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository("sessionUserMappingRepository")
public class JdbcSessionUserMappingRepository implements SessionUserMappingRepository {
    private final JdbcOperations jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    public JdbcSessionUserMappingRepository(JdbcOperations jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public int save(SessionUser sessionUserMapping) {
        String sql = "insert into session_user_mapping (id, session_id, ns_user_id, created_at) values(?, ?, ?, ?)";
        return jdbcTemplate.update(sql,sessionUserMapping.getId(), sessionUserMapping.getSessionId(), sessionUserMapping.getNsUserId(), LocalDateTime.now());
    }

    @Override
    public SessionUser findById(Long id) {
        String sql = "select id, session_id, ns_user_id, created_at from session_user_mapping where id = ?";
        RowMapper<SessionUser> rowMapper = (rs, rowNum) -> new SessionUser(
                rs.getLong(1),
                rs.getLong(2),
                rs.getLong(3),
                toLocalDateTime(rs.getTimestamp(4)));
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public List<SessionUser> findBySessionId(Long sessionId) {
        String sql = "select id, session_id, ns_user_id, created_at from session_user_mapping where session_id = ?";
        RowMapper<SessionUser> rowMapper = (rs, rowNum) -> new SessionUser(
                rs.getLong(1),
                rs.getLong(2),
                rs.getLong(3),
                toLocalDateTime(rs.getTimestamp(4)));
        return jdbcTemplate.query(sql, rowMapper, sessionId);
    }

    @Override
    public int update(SessionUser sessionUserMapping) {
        String sql = "update session_user_mapping set session_id=?, ns_user_id=?, updated_at=? where id = ?";
        return jdbcTemplate.update(sql, sessionUserMapping.getSessionId(), sessionUserMapping.getNsUserId(), LocalDateTime.now(), sessionUserMapping.getId());
    }

    @Override
    public int deleteById(Long id) {
        String sql = "delete from session_user_mapping where id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public int deleteBySessionId(Long sessionId) {
        String sql = "delete from session_user_mapping where session_id = ?";
        return jdbcTemplate.update(sql, sessionId);
    }

    @Override
    public Optional<List<SessionUser>> findBySessionIds(List<Long> sessionIds) {
        String sql = "select id, session_id, ns_user_id, created_at from session_user_mapping where session_id IN  = (:values)";

        MapSqlParameterSource parameters = new MapSqlParameterSource("values", sessionIds);
        RowMapper<SessionUser> rowMapper = (rs, rowNum) -> new SessionUser(
                rs.getLong(1),
                rs.getLong(2),
                rs.getLong(3),
                toLocalDateTime(rs.getTimestamp(4)));

        return Optional.of(namedParameterJdbcTemplate.query(sql, parameters, rowMapper));
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
