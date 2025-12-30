package nextstep.courses.infrastructure;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import nextstep.courses.domain.enrollment.Enrollment;
import nextstep.courses.domain.enrollment.Enrollments;
import nextstep.courses.domain.image.CoverImage;
import nextstep.courses.domain.policy.FreeSessionPolicy;
import nextstep.courses.domain.policy.PaidSessionPolicy;
import nextstep.courses.domain.policy.SessionPolicy;
import nextstep.courses.domain.policy.SessionType;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionPeriod;
import nextstep.courses.domain.session.SessionRepository;
import nextstep.courses.domain.session.SessionStatus;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {

    private JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Session session) {
        String sql = "insert into session "
                + "(start_date, end_date, cover_image_file_name, cover_image_size, cover_image_type, "
                + "cover_image_width, cover_image_height, policy_type, price, capacity, session_status, created_at) "
                + "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        return jdbcTemplate.update(
                sql, session.period().startDate(), session.period().endDate(),
                session.coverImageName(), session.coverImageSize(), session.coverImageType(),
                session.coverImageWidth(), session.coverImageHeight(),
                session.type().name(), session.price(), session.capacity(), session.status().name(), LocalDateTime.now()
        );
    }

    @Override
    public Session findById(Long id) {
        String sql = "select id, start_date, end_date, cover_image_file_name, cover_image_size, cover_image_type, "
                + "cover_image_width, cover_image_height, policy_type, price, capacity, session_status, created_at "
                + "from session where id = ?";
        RowMapper<Session> rowMapper = (rs, rowNum) -> new Session(
                rs.getLong("id"),
                mapSessionPeriod(rs),
                mapCoverImage(rs),
                mapPolicy(rs),
                SessionStatus.valueOf(rs.getString("session_status")),
                findEnrollments(id));
        return jdbcTemplate.queryForObject(sql,rowMapper, id);
    }

    private SessionPeriod mapSessionPeriod(ResultSet rs) throws SQLException {
        return new SessionPeriod(
                toLocalDate(rs.getTimestamp("start_date")),
                toLocalDate(rs.getTimestamp("end_date"))
        );
    }

    private CoverImage mapCoverImage(ResultSet rs) throws SQLException {
        return new CoverImage(
                rs.getString("cover_image_file_name"),
                rs.getLong("cover_image_size"),
                rs.getInt("cover_image_width"),
                rs.getInt("cover_image_height")
        );
    }

    private SessionPolicy mapPolicy(ResultSet rs) throws SQLException {
        SessionType sessionType = SessionType.valueOf(rs.getString("policy_type"));

        if (sessionType == SessionType.FREE) {
            return new FreeSessionPolicy();
        }
        int price = rs.getInt("price");
        int capacity = rs.getInt("capacity");
        return new PaidSessionPolicy(price, capacity);
    }

    private LocalDate toLocalDate(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime().toLocalDate();
    }

    private Enrollments findEnrollments(Long sessionId) {
        String sql = "select id, student_id, session_id from enrollment where session_id = ?";
        List<Enrollment> enrollments = jdbcTemplate.query(sql, (rs, rowNum) -> new Enrollment(
                rs.getLong("id"),
                rs.getLong("student_id"),
                rs.getLong("session_id")
        ), sessionId);
        return new Enrollments(enrollments);
    }
}
