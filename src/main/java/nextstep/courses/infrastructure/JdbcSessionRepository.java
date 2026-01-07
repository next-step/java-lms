package nextstep.courses.infrastructure;

import nextstep.courses.domain.enrollment.Capacity;
import nextstep.courses.domain.enrollment.Enrollment;
import nextstep.courses.domain.enrollment.EnrollmentPolicyFactory;
import nextstep.courses.domain.enrollment.Enrollments;
import nextstep.courses.domain.session.EnrollmentStatus;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionProgress;
import nextstep.courses.domain.session.SessionState;
import nextstep.courses.domain.session.cover.CoverImage;
import nextstep.courses.domain.session.cover.CoverImages;
import nextstep.courses.repository.CoverImageRepository;
import nextstep.courses.repository.EnrollmentRepository;
import nextstep.courses.repository.SessionRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {
    private final JdbcOperations jdbcTemplate;
    private final CoverImageRepository coverImageRepository;
    private final EnrollmentRepository enrollmentRepository;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate, CoverImageRepository coverImageRepository, EnrollmentRepository enrollmentRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.coverImageRepository = coverImageRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    @Override
    public int save(Session session) {
        String sql = "insert into session (id, course_id, start_at, end_at, policy_type," +
                "price, state, progress_status, enrollment_status, capacity, created_at, updated_at) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        int result = jdbcTemplate.update(sql, session.getId(), session.getCourseId(), session.getStartDate(), session.getEndDate()
                , session.getPolicyType(), session.getPrice(), session.getState()
                , session.getSessionProgress(), session.getEnrollmentStatus(), session.getCapacity()
                , LocalDateTime.now(), LocalDateTime.now());

        CoverImages coverImages = session.getCoverImages();
        for (CoverImage image : coverImages.getValues()) {
            coverImageRepository.save(image, session.getId());
        }

        return result;
    }

    @Override
    public Session findById(Long id) {
        String sql = "SELECT id, course_id, start_at, end_at, cover_image_size, cover_image_name, " +
                "cover_image_width, cover_image_height, policy_type, price, state, capacity, enrollment_status, progress_status " +
                "FROM session WHERE id = ?";

        CoverImages coverImages = coverImageRepository.findBySessionId(id);
        List<Enrollment> enrollments = enrollmentRepository.findBySessionId(id);

        RowMapper<Session> rowMapper = (rs, rowNum) -> new Session(
                rs.getLong("id"),
                rs.getLong("course_id"),
                toLocalDateTime(rs.getTimestamp("start_at")),
                toLocalDateTime(rs.getTimestamp("end_at")),
                coverImages,
                EnrollmentPolicyFactory.create(rs.getString("policy_type"), rs.getLong("price")),
                SessionState.valueOf(rs.getString("state")),
                new Enrollments(enrollments, new Capacity(rs.getInt("capacity"))),
                SessionStateMapper.toEnrollmentStatus(rs.getString("enrollment_status"), rs.getString("state")),
                SessionStateMapper.toProgress(rs.getString("progress_status"), rs.getString("state")),
                null
        );

        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public List<Session> findByCourseId(Long courseId) {
        String sql = "SELECT id, course_id, start_at, end_at, cover_image_size, cover_image_name, " +
                "cover_image_width, cover_image_height, policy_type, price, state, capacity, enrollment_status, progress_status " +
                "FROM session WHERE course_id = ?";

        RowMapper<Session> rowMapper = (rs, rowNum) -> {

            Long sessionId = rs.getLong("id");
            CoverImages coverImages = coverImageRepository.findBySessionId(sessionId);
            List<Enrollment> enrollments = enrollmentRepository.findBySessionId(sessionId);

            return new Session(
                    rs.getLong("id"),
                    rs.getLong("course_id"),
                    toLocalDateTime(rs.getTimestamp("start_at")),
                    toLocalDateTime(rs.getTimestamp("end_at")),
                    coverImages,
                    EnrollmentPolicyFactory.create(rs.getString("policy_type"), rs.getLong("price")),
                    SessionState.valueOf(rs.getString("state")),
                    new Enrollments(enrollments, new Capacity(rs.getInt("capacity"))),
                    SessionStateMapper.toEnrollmentStatus(rs.getString("enrollment_status"), rs.getString("state")),
                    SessionStateMapper.toProgress(rs.getString("progress_status"), rs.getString("state")),
                    null
            );
        };
        return jdbcTemplate.query(sql, rowMapper, courseId);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
