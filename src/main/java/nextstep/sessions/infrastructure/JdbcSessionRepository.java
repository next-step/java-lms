package nextstep.sessions.infrastructure;

import nextstep.sessions.domain.*;
import nextstep.users.domain.NsUser;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Session session) {
        String sql = "insert into session (course_id, payment_type, progress_status, recruitment_status, session_capacity, start_date, end_date, created_at) values(?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(
                sql,
                session.getCourseId(),
                session.getPaymentType().name(),
                session.getProgressStatus().name(),
                session.getRecruitmentStatus().name(),
                session.getSessionStudents().getMaximumCapacity(),
                session.getSessionPeriod().getStartDate(),
                session.getSessionPeriod().getEndDate(),
                session.getBaseTime().getCreatedDate());
    }

    @Override
    public Optional<Session> findById(Long id) {
        String sql = "SELECT id, course_id, start_date, end_date, payment_type, progress_status, recruitment_status, session_capacity FROM session WHERE id = ?";
        RowMapper<Session> rowMapper = (rs, rowNum) -> new Session(
                rs.getLong("id"),
                rs.getLong("course_id"),
                toLocalDateTime(rs.getTimestamp("start_date")),
                toLocalDateTime(rs.getTimestamp("end_date")),
                SessionPaymentType.of(rs.getString("payment_type")),
                SessionProgressStatus.of(rs.getString("progress_status")),
                SessionRecruitmentStatus.of(rs.getString("recruitment_status")),
                rs.getInt("session_capacity"));
        try {
            return Optional.of(jdbcTemplate.queryForObject(sql, rowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public int enrollUser(Long sessionId, NsUser nsUser) {
        String sql = "INSERT INTO session_student (session_id, ns_user_id) VALUES(?, ?)";
        return jdbcTemplate.update(sql, sessionId, nsUser.getId());
    }

    @Override
    public List<NsUser> findAllUsersBySessionId(Long sessionId) {
        String sql = "SELECT U.id, U.user_id, U.password, U.name, U.email, U.created_at, U.updated_at " +
                "FROM ns_user U " +
                "INNER JOIN session_student SU ON U.id = SU.ns_user_id WHERE SU.session_id = ?";
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
    public int saveCoverImage(Long sessionId, SessionCoverImage image) {
        String sql = "INSERT INTO cover_image (session_id, image_name, image_type, url, created_at) VALUES(?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, sessionId, image.getImageName(), image.getImageType().name(), image.getUrl().toString(), image.getBaseTime().getCreatedDate());
    }

    @Override
    public Optional<SessionCoverImage> findCoverImageBySessionId(Long sessionId) {
        String sql = "SELECT session_id, image_name, image_type, url FROM cover_image WHERE session_id = ?";
        RowMapper<SessionCoverImage> rowMapper = (rs, rowNum) -> new SessionCoverImage(
                sessionId,
                rs.getString("image_name"),
                SessionImageType.of(rs.getString("image_type")),
                rs.getString("url"));
        try {
            return Optional.of(jdbcTemplate.queryForObject(sql, rowMapper, sessionId));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
