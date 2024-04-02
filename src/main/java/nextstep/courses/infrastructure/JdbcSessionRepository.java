package nextstep.courses.infrastructure;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import nextstep.courses.domain.PaidSession;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionCoverImage;
import nextstep.courses.domain.SessionStatus;
import nextstep.users.domain.NsUser;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcSessionRepository {

    private final JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Session session) {
        if (session instanceof PaidSession) {
            save((PaidSession) session);
            return;
        }
        String sql = "INSERT INTO session (course_id, start_date, end_date, cover_image_id, status, created_at, updated_at) VALUES (?, ?, ?, ?, ?, , ?, ?)";
        jdbcTemplate.update(sql, session.getId(), session.getStartDate(), session.getEndDate(), session.getCoverImageId(),
            session.getStatusName(), session.getCreatedAt(), session.getUpdatedAt());
    }

    public void save(PaidSession paidSession) {
        String sql = "INSERT INTO session (course_id, start_date, end_date, cover_image_id, status, price, capacity, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, paidSession.getId(), paidSession.getStartDate(), paidSession.getEndDate(), paidSession.getCoverImageId(),
            paidSession.getStatusName(), paidSession.getPrice(), paidSession.getCapacity(), paidSession.getCreatedAt(), paidSession.getUpdatedAt());
    }

    public Session findById(Long id) {
        String sql = "SELECT s.*, "
            + "ci.id image_id, ci.file_byte_size, ci.ext, ci.width, ci.height, ci.url "
            + "FROM session s "
            + "JOIN cover_image ci "
            + "ON s.cover_image_id = ci.id "
            + "WHERE s.id = ?";
        return jdbcTemplate.queryForObject(sql, new SessionRowMapper(), id);
    }

    private class SessionRowMapper implements RowMapper<Session> {
        @Override
        public Session mapRow(ResultSet rs, int rowNum) throws SQLException {
            Long id = rs.getLong("id");
            LocalDateTime startDate = rs.getTimestamp("start_date").toLocalDateTime();
            LocalDateTime endDate = rs.getTimestamp("end_date").toLocalDateTime();
            SessionCoverImage coverImage = new SessionCoverImage(
                rs.getLong("image_id"), rs.getLong("file_byte_size"),
                rs.getString("ext"), rs.getInt("width"), rs.getInt("height"), rs.getString("url"));
            SessionStatus status = SessionStatus.valueOf(rs.getString("status"));
            LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();
            LocalDateTime updatedAt = Optional.ofNullable(rs.getTimestamp("updated_at"))
                .map(Timestamp::toLocalDateTime)
                .orElse(null);
            Set<NsUser> learners = loadLearners(id);
            Long price = rs.getLong("price");
            Integer capacity = rs.getInt("capacity");

            if (price != null) {
                return new PaidSession(id, startDate, endDate, coverImage, status, learners, createdAt, updatedAt, price, capacity);
            }
            return new Session(id, startDate, endDate, coverImage, status, learners, createdAt, updatedAt);
        }

        private Set<NsUser> loadLearners(Long sessionId) {
            String sql = "SELECT u.* FROM ns_user u JOIN session_learner sl ON u.id = sl.user_id WHERE sl.session_id = ?";
            return new HashSet<>(jdbcTemplate.query(sql, new NsUserRowMapper(), sessionId));
        }
    }

    private static class NsUserRowMapper implements RowMapper<NsUser> {
        @Override
        public NsUser mapRow(ResultSet rs, int rowNum) throws SQLException {
            long id = rs.getLong("id");
            String userId = rs.getString("user_id");
            String password = rs.getString("password");
            String name = rs.getString("name");
            String email = rs.getString("email");
            LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();
            LocalDateTime updatedAt = Optional.ofNullable(rs.getTimestamp("updated_at"))
                .map(Timestamp::toLocalDateTime)
                .orElse(null);
            return new NsUser(id, userId, password, name, email, createdAt, updatedAt);
        }
    }
}
