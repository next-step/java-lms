package nextstep.sessions.infrastructure;

import nextstep.common.Period;
import nextstep.sessions.domain.Session;
import nextstep.sessions.domain.SessionCharge;
import nextstep.sessions.domain.SessionImage;
import nextstep.sessions.domain.SessionRepository;
import nextstep.sessions.domain.SessionStatus;
import nextstep.sessions.domain.SessionStudent;
import nextstep.sessions.domain.SessionStudents;
import nextstep.users.domain.NsUser;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class JdbcSessionRepository implements SessionRepository {

    private JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long save(Session session) {
        String sql = "insert into session (name, start_at, end_at, image_size, image_width, image_height, image_type, price, limit_count, status, created_at)" +
                " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, new String[]{"ID"});
            ps.setString(1, session.getName());
            ps.setDate(2, Date.valueOf(session.getDate().getStartAt()));
            ps.setDate(3, Date.valueOf(session.getDate().getEndAt()));
            ps.setInt(4, session.getImage().getSize());
            ps.setDouble(5, session.getImage().getWidth());
            ps.setDouble(6, session.getImage().getHeight());
            ps.setString(7, session.getImage().getType().toString());
            ps.setDouble(8, session.getCharge().getPrice());
            ps.setInt(9, session.getCharge().getLimitCount());
            ps.setString(10, session.getStatus().toString());
            ps.setTimestamp(11, Timestamp.valueOf(session.getCreatedAt()));
            return ps;
        }, keyHolder);
        return (Long) keyHolder.getKey();
    }

    @Override
    public Session findById(Long id) {
        String sql = "select id, name, start_at, end_at, image_size, image_width, image_height, image_type, price, limit_count, status, created_at, updated_at from session where id = ?";
        RowMapper<Session> rowMapper = ((rs, rowNum) -> new Session(
                rs.getLong(1),
                rs.getString(2),
                new Period(toLocalDate(rs.getTimestamp(3)), toLocalDate(rs.getTimestamp(4))),
                new SessionImage(rs.getInt(5), rs.getDouble(6), rs.getDouble(7), rs.getString(8)),
                new SessionCharge(rs.getInt(9) > 0 ? true : false, rs.getLong(9), rs.getInt(10)),
                SessionStatus.valueOf(rs.getString(11)),
                studentsFindBySessionId(id),
                toLocalDateTime(rs.getTimestamp(12)),
                toLocalDateTime(rs.getTimestamp(13))
        ));
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    private SessionStudents studentsFindBySessionId(Long id) {
        String sql = "select ss.id, ss.user_id, ss.registration_at, nu.user_id, nu.password, nu.name, nu.email, nu.created_at, nu.updated_at " +
                "from session_student ss join ns_user nu on ss.user_id = nu.id " +
                "where session_id = ?";

        RowMapper<SessionStudent> rowMapper = ((rs, rowNum) -> new SessionStudent(
                rs.getLong(1),
                new NsUser(
                        rs.getLong(2),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        toLocalDateTime(rs.getTimestamp(8)),
                        toLocalDateTime(rs.getTimestamp(9))
                ),
                toLocalDateTime(rs.getTimestamp(3))
        ));
        List<SessionStudent> students = jdbcTemplate.query(sql, new String[]{String.valueOf(id)}, rowMapper);
        if (students.isEmpty()) {
            return new SessionStudents();
        }
        return new SessionStudents(students);
    }

    @Override
    public Long enroll(Session session, SessionStudent student) {
        String sql = "insert into session_student (user_id, registration_at, session_id) values (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, new String[]{"ID"});
            ps.setLong(1, student.getUser().getId());
            ps.setTimestamp(2, Timestamp.valueOf(student.getRegistrationAt()));
            ps.setLong(3, session.getId());
            return ps;
        }, keyHolder);
        return (long) keyHolder.getKey();
    }

    private LocalDate toLocalDate(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime().toLocalDate();
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
