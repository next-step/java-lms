package nextstep.courses.infrastructure;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import nextstep.courses.domain.SessionRepository;
import nextstep.courses.domain.session.EnrollmentPolicy;
import nextstep.courses.domain.session.Period;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionStatus;
import nextstep.courses.domain.session.SessionType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {
    private JdbcTemplate jdbcTemplate;

    public JdbcSessionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long save(Session session) {
        String sql = "insert into session (course_id, start_date, end_date, status, session_type, capacity, fee, deleted, created_at) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setLong(1, session.getCourseId());
            ps.setDate(2, Date.valueOf(session.getStartDate()));
            ps.setDate(3, Date.valueOf(session.getEndDate()));
            ps.setString(4, session.getSessionStatusName());
            ps.setString(5, session.getSessionTypeName());
            ps.setInt(6, session.getCapacity());
            ps.setLong(7, session.getFee());
            ps.setBoolean(8, session.isDeleted());
            ps.setTimestamp(9, Timestamp.valueOf(session.getCreatedAt()));
            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();
        if (key == null) {
            throw new IllegalStateException("세션 저장 중 키를 가져올 수 없습니다.");
        }
        return key.longValue();
    }


    @Override
    public Session findById(Long id) {
        String sql = "select id, course_id, start_date, end_date, status, session_type, capacity, fee, deleted, created_at, updated_at from session where id = ?";
        RowMapper<Session> rowMapper = (rs, rowNum) -> new Session(
            rs.getLong("id"),
            rs.getLong("course_id"),
            new Period(rs.getDate("start_date").toLocalDate(), rs.getDate("end_date").toLocalDate()),
            SessionStatus.valueOf(rs.getString("status")),
            createEnrollmentPolicy(rs),
            toLocalDateTime(rs.getTimestamp("created_at")),
            toLocalDateTime(rs.getTimestamp("updated_at"))
        );
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public List<Session> findByCourseId(Long courseId) {
        String sql = "select id, course_id, start_date, end_date, status, session_type, capacity, fee, deleted, created_at, updated_at from session where course_id = ?";
        RowMapper<Session> rowMapper = (rs, rowNum) -> new Session(
            rs.getLong("id"),
            rs.getLong("course_id"),
            new Period(rs.getDate("start_date").toLocalDate(), rs.getDate("end_date").toLocalDate()),
            SessionStatus.valueOf(rs.getString("status")),
            createEnrollmentPolicy(rs),
            toLocalDateTime(rs.getTimestamp("created_at")),
            toLocalDateTime(rs.getTimestamp("updated_at"))
        );
        return jdbcTemplate.query(sql, rowMapper, courseId);
    }

    private EnrollmentPolicy createEnrollmentPolicy(ResultSet rs) throws SQLException {
        SessionType type = SessionType.valueOf(rs.getString("session_type"));
        if (type == SessionType.FREE) {
            return EnrollmentPolicy.free();
        }
        return EnrollmentPolicy.paid(rs.getInt("capacity"), rs.getLong("fee"));
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
