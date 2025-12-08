package nextstep.courses.infrastructure.jdbc;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import nextstep.courses.domain.session.SessionState;
import nextstep.courses.infrastructure.entity.RegistrationEntity;
import nextstep.courses.infrastructure.entity.SessionCoverImageEntity;
import nextstep.courses.infrastructure.entity.SessionEntity;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class SessionJdbcDao {
    private final JdbcOperations jdbcTemplate;

    public SessionJdbcDao(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int save(SessionEntity entity) {
        String sql = "insert into session (course_id, term, start_day, end_day, state, type, max_capacity, tuition_fee, created_at) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";

        return jdbcTemplate.update(sql,
            entity.getCourseId(),
            entity.getTerm(),
            Date.valueOf(entity.getStartDay()),
            Date.valueOf(entity.getEndDay()),
            entity.getState(),
            entity.getType(),
            entity.getMaxCapacity(),
            entity.getTuitionFee(),
            entity.getCreatedAt()
        );
    }

    public int saveCoverImage(SessionCoverImageEntity image) {
        String sql = "insert into session_cover_image (session_id, width, height, extension, capacity) values(?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
            image.getSessionId(),
            image.getWidth(),
            image.getHeight(),
            image.getExtension(),
            image.getCapacity()
        );
    }

    public SessionEntity findById(Long id) {
        String sql = "select id, course_id, term, start_day, end_day, state, type, max_capacity, tuition_fee, created_at from session where id = ?";
        return jdbcTemplate.queryForObject(sql, sessionRowMapper(), id);
    }

    public SessionCoverImageEntity findCoverImageBySessionId(Long sessionId) {
        String sql = "select id, session_id, width, height, extension, capacity from session_cover_image where session_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, coverImageRowMapper(), sessionId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<RegistrationEntity> findRegistrationsBySessionId(Long sessionId) {
        String sql = "select id, session_id, student_id, enrolled_at from registration where session_id = ?";
        return jdbcTemplate.query(sql, registrationRowMapper(), sessionId);
    }

    public int updateState(Long id, SessionState state) {
        String sql = "update session set state = ? where id = ?";
        return jdbcTemplate.update(sql, state.name(), id);
    }

    private RowMapper<SessionEntity> sessionRowMapper() {
        return (rs, rowNum) -> new SessionEntity(
            rs.getLong("id"),
            rs.getLong("course_id"),
            rs.getInt("term"),
            toLocalDate(rs.getDate("start_day")),
            toLocalDate(rs.getDate("end_day")),
            rs.getString("state"),
            rs.getString("type"),
            rs.getObject("max_capacity", Integer.class),
            rs.getObject("tuition_fee", Long.class),
            rs.getTimestamp("created_at") != null ? rs.getTimestamp("created_at").toLocalDateTime() : null
        );
    }

    private RowMapper<SessionCoverImageEntity> coverImageRowMapper() {
        return (rs, rowNum) -> new SessionCoverImageEntity(
            rs.getLong("id"),
            rs.getLong("session_id"),
            rs.getInt("width"),
            rs.getInt("height"),
            rs.getString("extension"),
            rs.getLong("capacity")
        );
    }

    private RowMapper<RegistrationEntity> registrationRowMapper() {
        return (rs, rowNum) -> new RegistrationEntity(
            rs.getLong("id"),
            rs.getLong("session_id"),
            rs.getLong("student_id"),
            rs.getTimestamp("enrolled_at").toLocalDateTime()
        );
    }

    private LocalDate toLocalDate(Date date) {
        if (date == null) {
            return null;
        }
        return date.toLocalDate();
    }
}