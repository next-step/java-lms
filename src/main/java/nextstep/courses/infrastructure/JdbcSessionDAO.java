package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionCondition;
import nextstep.courses.domain.session.SessionPeriod;
import nextstep.courses.domain.session.SessionStatus;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository("sessionDAO")
public class JdbcSessionDAO implements SessionDAO {
    private final JdbcOperations jdbcTemplate;

    public JdbcSessionDAO(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long save(Session session) {
        String sql = "insert into session " +
                "(course_id, generation, started_at, finished_at, created_at,session_progress_status, session_recruitment_status, amount, max_user, approval_required, teacher_id, user_number) " +
                "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setLong(1, session.courseId());
            ps.setLong(2, session.generation());
            ps.setObject(3, session.sessionPeriod().startedAt());
            ps.setObject(4, session.sessionPeriod().finishedAt());
            ps.setObject(5, LocalDateTime.now());
            ps.setString(6, session.sessionStatus().sessionProgressStatus().name());
            ps.setString(7, session.sessionStatus().sessionRecruitmentStatus().name());
            ps.setLong(8, session.sessionCondition().amount());
            ps.setLong(9, session.sessionCondition().maxUserNumber());
            ps.setBoolean(10, session.approvalRequired());
            ps.setLong(11, session.teacherId());
            ps.setLong(12, session.sessionCondition().userNumber());
            return ps;
        }, keyHolder);

        return (Long) keyHolder.getKey();
    }

    @Override
    public Session findById(Long id) {
        String sql = "select id, course_id, generation, started_at, finished_at, session_progress_status, session_recruitment_status, amount, max_user, approval_required, teacher_id, user_number from session where id = ?";
        RowMapper<Session> rowMapper = (rs, rowNum) -> new Session(
                rs.getLong(1),
                rs.getLong(2),
                rs.getLong(3),
                new SessionPeriod(toLocalDateTime(rs.getTimestamp(4)), toLocalDateTime(rs.getTimestamp(5))),
                new SessionStatus(rs.getString(6), rs.getString(7)),
                new SessionCondition(rs.getLong(8), rs.getLong(9), rs.getLong(12)),
                rs.getBoolean(10),
                rs.getLong(11)
        );
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public int updateSessionUserNumber(Session session) {
        String sql = "update session set user_number = ? where id = ?";
        return jdbcTemplate.update(sql, session.sessionCondition().userNumber(), session.id());
    }

    @Override
    public List<Student> findStudentsBySessionId(Long sessionId) {
        String sql = "select session_id, ns_user_id, enrollment_status from student where session_id=?";
        RowMapper<Student> rowMapper = (rs, rowNum) -> new Student(rs.getLong(1), rs.getLong(2), rs.getString(3));
        return jdbcTemplate.query(sql, rowMapper, sessionId);
    }

    @Override
    public int saveStudent(Student nsUserSession) {
        String sql = "insert into student(session_id, ns_user_id, enrollment_status) values (?, ?, ?)";
        return jdbcTemplate.update(sql, nsUserSession.sessionId(), nsUserSession.nsUserId(), nsUserSession.enrollmentStatus().name());
    }

    @Override
    public int updateStudent(Student nsUserSession) {
        String sql = "update student set enrollment_status = ? where session_id = ? and ns_user_id = ?";
        return jdbcTemplate.update(sql, nsUserSession.enrollmentStatus().name(), nsUserSession.sessionId(), nsUserSession.nsUserId());
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
