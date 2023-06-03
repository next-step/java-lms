package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import nextstep.courses.domain.enrollment.SessionEnrollment;
import nextstep.courses.domain.enrollment.SessionStatus;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {

    private final JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcOperations) {
        this.jdbcTemplate = jdbcOperations;
    }

    @Override
    public int save(Session session) {
        String sql = "insert into session(course_id, owner_id, title, image_url," +
                " charge_type, status_type, created_at, closed_at, capacity)" +
                " values(?, ?, ?, ?, ?, ?, ?, ?, ?)";

        return jdbcTemplate.update(
                sql,
                session.getCourseId(),
                session.getOwnerId(),
                session.getTitle(),
                session.getCoverImageInfo(),
                session.getSessionType().toString(),
                session.getStatus().toString(),
                session.getCreateAt(),
                session.getCloseAt(),
                session.getTotalStudentNum()
        );
    }

    @Override
    public Session findById(Long id) {
        String sql = "select course_id, owner_id, title, image_url, charge_type, status_type, " +
                " created_at, closed_at, capacity" +
                " from session" +
                " where id = ?";

        return jdbcTemplate.queryForObject(sql, generateRowMapper(), id);
    }

    @Override
    public List<Session> findByCourseId(Long courseId) {
        String sql = "select course_id, owner_id, title, image_url, charge_type, status_type, created_at" +
                ", closed_at, capacity" +
                " from course" +
                " where course_id = ?";

        return jdbcTemplate.queryForList(sql, Session.class, generateRowMapper(), courseId);
    }

    private RowMapper<Session> generateRowMapper() {
        return (rs, rowNum) -> new Session(
                        new SessionInfo(rs.getLong(1), rs.getLong(2), rs.getString(3), rs.getString(4),
                        SessionType.find(rs.getString(5))),
                        new SessionEnrollment(SessionStatus.find(rs.getString(6)), rs.getLong(9)),
                        new SessionTimeLine(rs.getTimestamp(7).toLocalDateTime(), rs.getTimestamp(8).toLocalDateTime())
                        );
    }
}
