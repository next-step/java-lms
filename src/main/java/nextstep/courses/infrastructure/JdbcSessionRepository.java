package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
        String sql = "insert into session (course_id, title, creator_id, created_at, start_date, end_date, image_url, pay_type, session_state, max_count) values(?, ?, ?, ?, ?, ? ,?, ?, ?, ?)";

        return jdbcTemplate.update(sql, session.getCourseId(), session.getTitle(), session.creatorId(), session.createAt(), session.startDate(), session.endDate(), session.imageUrl() ,session.sessionType(), SessionState.PREPARING.name(), session.StudentsMaxCount());

    }

    @Override
    public Session findById(Long id) {
        String sql = "select id, title, creator_id, created_at, updated_at, start_date, end_date, image_url, pay_type, max_count, session_state, course_id from session where id = ?";
        RowMapper<Session> rowMapper = (rs, rowNum) -> Session.builder()
                .id(rs.getLong(1))
                .title(rs.getString(2))
                .creatorId(rs.getLong(3))
                .createAt(toLocalDateTime(rs.getTimestamp(4)))
                .updateAt(toLocalDateTime(rs.getTimestamp(5)))
                .period(new Period(toLocalDateTime(rs.getTimestamp(6)), toLocalDateTime(rs.getTimestamp(7))))
                .imageUrl(rs.getString(8))
                .sessionType(SessionType.convert(rs.getString(9)))
                .students(new Students((int) rs.getLong(10)))
                .sessionValidator(new SessionValidator( rs.getString(11)))

                .courseId(rs.getLong(12))
                .build();
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public List<Session> findByCourseId(Long courseId) {
        String sql = "select course_id, owner_id, title, image_url, charge_type, status_type, created_at, closed_at, max_student" +
                " from session" +
                " where course_id = ?";

        RowMapper<Session> rowMapper = (rs, rowNum) -> Session.builder()
                .id(rs.getLong(1))
                .title(rs.getString(2))
                .creatorId(rs.getLong(3))
                .createAt(toLocalDateTime(rs.getTimestamp(4)))
                .updateAt(toLocalDateTime(rs.getTimestamp(5)))
                .period(new Period(toLocalDateTime(rs.getTimestamp(6)), toLocalDateTime(rs.getTimestamp(7))))
                .imageUrl(rs.getString(8))
                .sessionType(SessionType.convert(rs.getString(9)))
                .sessionValidator(new SessionValidator(rs.getString(11)))

                .build();

        return jdbcTemplate.query(sql, rowMapper, courseId);
    }


    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
