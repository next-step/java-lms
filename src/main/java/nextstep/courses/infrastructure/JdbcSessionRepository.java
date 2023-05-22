package nextstep.courses.infrastructure;

import nextstep.courses.domain.Enrollment;
import nextstep.courses.domain.Image;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionTime;
import nextstep.courses.domain.repository.EnrollmentRepository;
import nextstep.courses.domain.repository.ImageRepository;
import nextstep.courses.domain.repository.SessionRepository;
import nextstep.courses.domain.repository.SessionTimeRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionTemplate;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {
    private final JdbcTemplate jdbcTemplate;
    private final TransactionTemplate transactionTemplate;
    private final ImageRepository imageRepository;
    private final SessionTimeRepository sessionTimeRepository;
    private final EnrollmentRepository enrollmentRepository;

    public JdbcSessionRepository(JdbcTemplate jdbcTemplate, TransactionTemplate transactionTemplate, ImageRepository imageRepository, SessionTimeRepository sessionTimeRepository, EnrollmentRepository enrollmentRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.transactionTemplate = transactionTemplate;
        this.imageRepository = imageRepository;
        this.sessionTimeRepository = sessionTimeRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    @Override
    public long save(Session session) {
        String query = "INSERT INTO session (period, cover_image_id, session_time_id, session_type, session_status, enrollment_id, maximum_enrollment) VALUES (?, ?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, session.getPeriod());
            ps.setLong(2, imageRepository.save(session.getCoverImage()));
            ps.setLong(3, sessionTimeRepository.save(session.getSessionTime()));
            ps.setString(4, session.getSessionType().toString());
            ps.setString(5, session.getSessionStatus().toString());
            ps.setLong(6, enrollmentRepository.save(session.getEnrollment()));
            ps.setInt(7, session.getMaximumEnrollment());
            return ps;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }


    @Override
    public Session findById(Long id) {
        String query = "SELECT * FROM session WHERE id = ?";
        return jdbcTemplate.queryForObject(query, sessionRowMapper(), id);
    }

    private RowMapper<Session> sessionRowMapper() {
        return (rs, rowNum) -> {
            Image coverImage = imageRepository.findById(rs.getLong("cover_image_id"));
            SessionTime sessionTime = sessionTimeRepository.findById(rs.getLong("session_time_id"));
            Enrollment enrollment = enrollmentRepository.findById(rs.getLong("enrollment_id"));
            return new Session(
                    rs.getLong("id"),
                    rs.getString("period"),
                    coverImage,
                    sessionTime,
                    rs.getString("session_type"),
                    rs.getString("session_status"),
                    enrollment,
                    rs.getInt("maximum_enrollment")
            );
        };
    }

    @Override
    public int update(Session session) {
        String query = "UPDATE session SET period = ?, cover_image_id = ?, session_time_id = ?, session_type = ?, session_status = ?, enrollment_id = ?, maximum_enrollment = ? WHERE id = ?";
        int updatedCount = 0;
        updatedCount += imageRepository.update(session.getCoverImage());
        updatedCount += sessionTimeRepository.update(session.getSessionTime());
        updatedCount += enrollmentRepository.update(session.getEnrollment());
        updatedCount += jdbcTemplate.update(query, session.getPeriod(), session.getCoverImage().getId(), session.getSessionTime().getId(), session.getSessionType().toString(), session.getSessionStatus().toString(), session.getEnrollment().getId(), session.getMaximumEnrollment(), session.getId());
        return updatedCount;
    }

    @Override
    public int delete(Long id) {
        Session session = findById(id);
        return transactionTemplate.execute(status -> {
            int deletedCount = 0;
            deletedCount += imageRepository.delete(session.getCoverImage().getId());
            deletedCount += sessionTimeRepository.delete(session.getSessionTime().getId());
            deletedCount += enrollmentRepository.delete(session.getEnrollment().getId());

            String deleteSession = "DELETE FROM session WHERE id = ?";
            deletedCount += jdbcTemplate.update(deleteSession, id);

            return deletedCount;
        });
    }

    @Override
    public List<Session> findSessionsByCourseId(Long courseId) {
        String sql = "SELECT * FROM session WHERE course_id = ?";
        return jdbcTemplate.query(sql, sessionRowMapper(), courseId);
    }

}
