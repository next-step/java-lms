package nextstep.courses.infrastructure;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import nextstep.courses.domain.image.SessionCoverImage;
import nextstep.courses.domain.registration.Registrations;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionRepository;
import nextstep.courses.domain.session.SessionState;
import nextstep.courses.infrastructure.entity.RegistrationEntity;
import nextstep.courses.infrastructure.entity.SessionEntity;
import nextstep.courses.infrastructure.entity.SessionCoverImageEntity;
import nextstep.courses.infrastructure.mapper.RegistrationMapper;
import nextstep.courses.infrastructure.mapper.SessionMapper;
import nextstep.courses.infrastructure.mapper.SessionCoverImageMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {
    private final JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Session session) {
        SessionEntity entity = SessionMapper.toEntity(session);

        String sql = "insert into session (course_id, term, start_day, end_day, state, type, max_capacity, tuition_fee, created_at) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";

        int result = jdbcTemplate.update(sql,
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

        if (session.getCoverImage() != null) {
            saveCoverImage(session.getCoverImage());
        }

        return result;
    }

    private void saveCoverImage(SessionCoverImage image) {
        SessionCoverImageEntity entity = SessionCoverImageMapper.toEntity(image);
        String sql = "insert into session_cover_image (session_id, width, height, extension, capacity) values(?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
            entity.getSessionId(),
            entity.getWidth(),
            entity.getHeight(),
            entity.getExtension(),
            entity.getCapacity()
        );
    }

    @Override
    public Session findById(Long id) {
        String sql = "select id, course_id, term, start_day, end_day, state, type, max_capacity, tuition_fee, created_at from session where id = ?";

        SessionEntity entity = jdbcTemplate.queryForObject(sql, sessionRowMapper(), id);
        Registrations registrations = findRegistrationsBySessionId(id, entity.getMaxCapacity());
        SessionCoverImage coverImage = findCoverImageBySessionId(id);
        return SessionMapper.toDomain(entity, registrations, coverImage);
    }

    private SessionCoverImage findCoverImageBySessionId(Long sessionId) {
        String sql = "select id, session_id, width, height, extension, capacity from session_cover_image where session_id = ?";
        try {
            SessionCoverImageEntity entity = jdbcTemplate.queryForObject(sql, coverImageRowMapper(), sessionId);
            return SessionCoverImageMapper.toDomain(entity);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
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

    @Override
    public int updateState(Long id, SessionState state) {
        String sql = "update session set state = ? where id = ?";
        return jdbcTemplate.update(sql, state.name(), id);
    }

    private Registrations findRegistrationsBySessionId(Long sessionId, Integer maxCapacity) {
        String sql = "select id, session_id, student_id, enrolled_at from registration where session_id = ?";

        List<RegistrationEntity> entities = jdbcTemplate.query(sql, registrationRowMapper(), sessionId);
        int capacity = maxCapacity != null ? maxCapacity : -1;
        return RegistrationMapper.toDomain(entities, capacity);
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
