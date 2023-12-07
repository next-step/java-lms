package nextstep.courses.infrastructure;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Long save(Session session) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "insert into session (course_id, start_date, end_date, image_id, paid_type," +
                " max_student_number, applied_number, session_fee, session_status, created_at) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setLong(1, session.getCourseId());
            ps.setTimestamp(2, Timestamp.valueOf(session.getPeriod().getStartDate()));
            ps.setTimestamp(3, Timestamp.valueOf(session.getPeriod().getEndDate()));
            ps.setLong(4, session.getImage().getId());
            ps.setString(5, session.getPaidType().toString());
            ps.setInt(6, session.getMaxStudentNumber());
            ps.setInt(7, session.getAppliedNumber());
            ps.setLong(8, session.getSessionFee());
            ps.setString(9, session.getSessionStatus().toString());
            ps.setTimestamp(10, Timestamp.valueOf(session.getCreatedAt()));
            return ps;
        }, keyHolder);
        return (Long) keyHolder.getKey();
    }

    @Override
    public Session findById(Long id) {
        String sql = "select id, course_id, start_date, end_date, image_id, paid_type, max_student_number," +
                "applied_number, session_fee, session_status, created_at, updated_at from session where id = ?";
        RowMapper<Session> rowMapper = ((rs, rowNum) -> new Session(
                rs.getLong(1),
                rs.getLong(2),
                toLocalDateTime(rs.getTimestamp(3)),
                toLocalDateTime(rs.getTimestamp(4)),
                rs.getLong(5),
                rs.getString(6),
                rs.getInt(7),
                rs.getInt(8),
                rs.getLong(9),
                rs.getString(10),
                toLocalDateTime(rs.getTimestamp(11)),
                toLocalDateTime(rs.getTimestamp(12))));
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
