package nextstep.courses.infrastructure;

import nextstep.courses.domain.enrollment.Capacity;
import nextstep.courses.domain.enrollment.EnrollmentPolicyFactory;
import nextstep.courses.domain.enrollment.Enrollments;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionState;
import nextstep.courses.repository.SessionRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Session session) {
        String sql = "insert into session (id, course_id, start_at, end_at, cover_image_size, cover_image_name, cover_image_width, cover_image_height, policy_type," +
                "price, state, capacity, created_at, updated_at) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, session.getId(), session.getCourseId(), session.getStartDate(), session.getEndDate(), session.getCoverImageSize(), session.getCoverImageName()
                , session.getCoverImageWidth(), session.getCoverImageHeight(), session.getPolicyType(), session.getPrice(), session.getState(), session.getCapacity()
                , LocalDateTime.now(), LocalDateTime.now());
    }

    @Override
    public Session findById(Long id) {
        String sql = "SELECT id, course_id, start_at, end_at, cover_image_size, cover_image_name, " +
                "cover_image_width, cover_image_height, policy_type, price, state, capacity " +
                "FROM session WHERE id = ?";

        RowMapper<Session> rowMapper = (rs, rowNum) -> new Session(
                rs.getLong("id"),
                rs.getLong("course_id"),
                toLocalDateTime(rs.getTimestamp("start_at")),
                toLocalDateTime(rs.getTimestamp("end_at")),
                rs.getInt("cover_image_size"),
                rs.getString("cover_image_name"),
                rs.getInt("cover_image_width"),
                rs.getInt("cover_image_height"),
                EnrollmentPolicyFactory.create(rs.getString("policy_type"), rs.getLong("price")),
                SessionState.valueOf(rs.getString("state")),
                new Enrollments(new Capacity(rs.getInt("capacity")))
        );

        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public List<Session> findByCourseId(Long courseId) {
        String sql = "SELECT id, course_id, start_at, end_at, cover_image_size, cover_image_name, " +
                "cover_image_width, cover_image_height, policy_type, price, state, capacity " +
                "FROM session WHERE course_id = ?";
        RowMapper<Session> rowMapper = (rs, rowNum) -> new Session(
                rs.getLong("id"),
                rs.getLong("course_id"),
                toLocalDateTime(rs.getTimestamp("start_at")),
                toLocalDateTime(rs.getTimestamp("end_at")),
                rs.getInt("cover_image_size"),
                rs.getString("cover_image_name"),
                rs.getInt("cover_image_width"),
                rs.getInt("cover_image_height"),
                EnrollmentPolicyFactory.create(rs.getString("policy_type"), rs.getLong("price")),
                SessionState.valueOf(rs.getString("state")),
                new Enrollments(new Capacity(rs.getInt("capacity")))
        );
        return jdbcTemplate.query(sql, rowMapper, courseId);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
