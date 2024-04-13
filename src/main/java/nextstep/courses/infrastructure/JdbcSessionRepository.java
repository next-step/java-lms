package nextstep.courses.infrastructure;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import nextstep.courses.domain.PaidSession;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionCoverImage;
import nextstep.courses.domain.SessionStatus;
import nextstep.users.domain.NsUser;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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
        String sessionSql = "INSERT INTO session (id, start_date, end_date, status, is_recruiting, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sessionSql, session.getId(), session.getStartDate(), session.getEndDate(),
            session.getStatusName(), session.isRecruiting(), session.getCreatedAt(), session.getUpdatedAt());

        saveCoverImages(session.getCoverImages(), session.getId());
    }

    public void save(PaidSession paidSession) {
        String sessionSql = "INSERT INTO session (id, start_date, end_date, status, is_recruiting, price, capacity, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sessionSql, paidSession.getId(), paidSession.getStartDate(), paidSession.getEndDate(), paidSession.getStatusName(), paidSession.isRecruiting(), paidSession.getPrice(), paidSession.getCapacity(), paidSession.getCreatedAt(), paidSession.getUpdatedAt());

        saveCoverImages(paidSession.getCoverImages(), paidSession.getId());
    }

    private void saveCoverImages(List<SessionCoverImage> coverImage, Long sessionId) {
        for (SessionCoverImage sessionCoverImage : coverImage) {
            saveCoverImage(sessionCoverImage, sessionId);
        }
    }

    private void saveCoverImage(SessionCoverImage coverImage, Long sessionId) {
        String coverImageSql = "INSERT INTO cover_image (id, session_id, file_byte_size, ext, width, height, url) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(coverImageSql, coverImage.getId(), sessionId, coverImage.getFileByteSize(), coverImage.getExt(),
            coverImage.getWidth(), coverImage.getHeight(), coverImage.getUrl());
    }

    public Session findById(Long id) {
        String sql = "SELECT * FROM session WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new SessionRowMapper(), id);
    }

    private class SessionRowMapper implements RowMapper<Session> {

        @Override
        public Session mapRow(ResultSet rs, int rowNum) throws SQLException {
            Long id = rs.getLong("id");
            LocalDateTime startDate = rs.getTimestamp("start_date").toLocalDateTime();
            LocalDateTime endDate = rs.getTimestamp("end_date").toLocalDateTime();
            List<SessionCoverImage> coverImages = loadCoverImages(id);
            SessionStatus status = SessionStatus.valueOf(rs.getString("status"));
            LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();
            LocalDateTime updatedAt = Optional.ofNullable(rs.getTimestamp("updated_at"))
                .map(Timestamp::toLocalDateTime)
                .orElse(null);
            Set<NsUser> learners = loadLearners(id);
            Long price = rs.getLong("price");
            Integer capacity = rs.getInt("capacity");
            boolean isRecruiting = rs.getBoolean("is_recruiting");

            if (price != null) {
                return new PaidSession(id, startDate, endDate, coverImages, status, isRecruiting, learners, createdAt, updatedAt, price, capacity);
            }
            return new Session(id, startDate, endDate, coverImages, status, isRecruiting, learners, createdAt, updatedAt);
        }

        private List<SessionCoverImage> loadCoverImages(Long sessionId) {
            String sql = "SELECT * FROM cover_image WHERE session_id = ?";
            return jdbcTemplate.query(sql, new CoverImageRowMapper(), sessionId);
        }

        private Set<NsUser> loadLearners(Long sessionId) {
            String sql = "SELECT u.* FROM ns_user u JOIN session_learner sl ON u.id = sl.user_id WHERE sl.session_id = ?";
            return new HashSet<>(jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(NsUser.class), sessionId));
        }
    }

    private class CoverImageRowMapper implements RowMapper<SessionCoverImage> {

        @Override
        public SessionCoverImage mapRow(ResultSet rs, int rowNum) throws SQLException {
            Long id = rs.getLong("id");
            Long fileByteSize = rs.getLong("file_byte_size");
            String ext = rs.getString("ext");
            int width = rs.getInt("width");
            int height = rs.getInt("height");
            String url = rs.getString("url");

            return new SessionCoverImage(id, fileByteSize, ext, width, height, url);
        }
    }
}
