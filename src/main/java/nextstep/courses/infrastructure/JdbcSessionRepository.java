package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

public class JdbcSessionRepository implements SessionRepository {

    private final JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Session session) {
        String sql = "insert into session (course_id, owner_id, title, image_url," +
                " charge_type, status_type, created_at, closed_at, max_student)" +
                " values(?, ?, ?, ?, ?, ?, ?, ?, ?)";

        return jdbcTemplate.update(
                sql,
                session.getSessionInfo().getCourseId(),
                session.getSessionInfo().getOwnerId(),
                session.getSessionInfo().getTitle(),
                session.getSessionInfo().getCoverImageInfo(),
                session.getSessionInfo().getSessionType().toString(),
                session.getStatus().toString(),
                session.getSessionTimeLine().getCreatedAt(),
                session.getSessionTimeLine().getClosedAt(),
                session.getMaxNumOfStudent()
        );
    }

    @Override
    public Session findById(Long id) {
        String sql = "select course_id, owner_id, title, image_url, charge_type, status_type, created_at, closed_at, max_student" +
                " from course" +
                " where id = ?";

        return jdbcTemplate.queryForObject(sql, generateRowMapper(), id);
    }


    @Override
    public List<Session> findByCourseId(Long courseId) {

        String sql = "select course_id, owner_id, title, image_url, charge_type, status_type, created_at, closed_at, max_student" +
                " from course" +
                " where course_id = ?";

        return jdbcTemplate.queryForList(sql, Session.class, generateRowMapper(), courseId);
    }

    private RowMapper<Session> generateRowMapper() {
        return (rs, rowNum) -> new Session(
                new SessionInfo(rs.getLong(1), rs.getLong(2), rs.getString(3), rs.getString(4), SessionType.find(rs.getString(5))),
                SessionStatus.find(rs.getString(6)),
                new SessionTimeLine(rs.getTimestamp(7).toLocalDateTime(), rs.getTimestamp(8).toLocalDateTime()),
                rs.getLong(9));
    }
}
