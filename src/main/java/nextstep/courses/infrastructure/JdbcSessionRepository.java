package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import nextstep.users.domain.NsStudent;
import nextstep.users.domain.NsStudentRepository;
import nextstep.users.domain.NsTeacher;
import nextstep.users.domain.NsTeacherRepository;
import nextstep.users.infrastructure.JdbcNsStudentRepository;
import nextstep.users.infrastructure.JdbcNsTeacherRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {

    private JdbcOperations jdbcTemplate;
    private NsTeacherRepository nsTeacherRepository;
    private NsStudentRepository nsStudentRepository;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.nsTeacherRepository = new JdbcNsTeacherRepository(jdbcTemplate);
    }

    @Override
    public int save(Session session) {
        String sql = "insert into session (name, created_at, updated_at, start_date, end_date, course_id) values(?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, session.getName(), session.getCreatedAt(), session.getUpdatedAt(), session.getStartDate(), session.getEndDate(), session.getCourseId());;
    }

    @Override
    public Session findById(Long id) {
        String sql = "select * from session where id = ?";
        RowMapper<Session> rowMapper = (rs, rowNum) -> new Session(
                rs.getLong(1),
                rs.getString(2),
                new SessionPeriod(toLocalDate(rs.getDate(3)), toLocalDate(rs.getDate(4))),
                rs.getString(5),
                new SessionOption(SessionType.find(rs.getString(6)),
                        SessionStatus.find(rs.getString(7)),
                        SessionEnrollment.find(rs.getString(8))),
                new SessionCapacity(rs.getInt(9),
                        rs.getInt(10)),
                new SessionStudent(),
                new NsTeacher(),
                rs.getLong(11),
                toLocalDateTime(rs.getTimestamp(12)),
                toLocalDateTime(rs.getTimestamp(13))
        );
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    private LocalDate toLocalDate(Date date) {
        if (date == null) {
            return null;
        }
        return date.toLocalDate();
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
