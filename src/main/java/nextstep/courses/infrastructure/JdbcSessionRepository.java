package nextstep.courses.infrastructure;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import nextstep.courses.domain.Status;
import nextstep.courses.domain.Type;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {

    private JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Session session) {
        String sql = "insert into session (name, created_at, updated_at, start_date, end_date, cover_image, payment_type, status, registered_student, max_student, course_id) values(?,?,?,?,?,?,?,?,?,?,?)";
        return jdbcTemplate.update(sql, session.getName(), session.getCreatedAt(), session.getUpdatedAt(), session.getStartDate()
                , session.getEndDate(), session.getCoverImage(), session.getType().toString(), session.getStatus().toString()
                , session.getNumberOfRegisteredStudent(), session.getMaxNumberOfStudents(), session.getCourseId());
    }

    @Override
    public Session findById(Long id) {
        String sql = "select * from session where id = ?";
        RowMapper<Session> rowMapper = (rs, rowNum) -> new Session(
                rs.getString(2),
                toLocalDate(rs.getDate(3)),
                toLocalDate(rs.getDate(4)),
                rs.getString(5),
                Type.find(rs.getString(6)),
                Status.find(rs.getString(7)),
                rs.getInt(8),
                rs.getInt(9),
                rs.getLong(10),
                toLocalDateTime(rs.getTimestamp(11)),
                toLocalDateTime(rs.getTimestamp(12))
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
