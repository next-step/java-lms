package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {
    private final JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Session session, Long courseId) {
        String sql = "insert into session (course_id,max_students,current_students,session_status_type,image_url,start_date,end_date,price,created_at) values(?,?,?,?,?,?,?,?,?)";
        return jdbcTemplate.update(
                sql,
                courseId,
                session.getSessionInfo().getMaxStudents(),
                session.getSessionInfo().getCurrentStudents(),
                session.getSessionInfo().getSessionStatusType().name(),
                session.getCoverImage().getImageUrl(),
                session.getSessionDate().getStartDate(),
                session.getSessionDate().getEndDate(),
                session.getPrice().getPrice(),
                session.getCreatedAt()
        );
    }

    @Override
    public Session findById(Long id) {
        String sql = "select id, max_students, current_students, session_status_type, image_url, start_date, end_date, price, created_at, updated_at from session where id = ?";
        RowMapper<Session> rowMapper = ((rs, rowNum) -> new Session(
                rs.getLong(1),
                toLocalDateTime(rs.getTimestamp(9)),
                toLocalDateTime(rs.getTimestamp(10)),
                new SessionInfo(
                        rs.getInt(2),
                        rs.getInt(3),
                        SessionStatusType.valueOf(rs.getString(4))
                ),
                new CoverImage(rs.getString(5)),
                new SessionDate(
                        toLocalDateTime(rs.getTimestamp(6)),
                        toLocalDateTime(rs.getTimestamp(7))
                ),
                new Price(rs.getInt(8))
        ));
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public List<Session> findByCourseId(Long courseId) {
        String sql = "select id, max_students, current_students, session_status_type, image_url, start_date, end_date, price, created_at, updated_at from session where course_id = ?";
        RowMapper<Session> rowMapper = ((rs, rowNum) -> new Session(
                rs.getLong(1),
                toLocalDateTime(rs.getTimestamp(9)),
                toLocalDateTime(rs.getTimestamp(10)),
                new SessionInfo(
                        rs.getInt(2),
                        rs.getInt(3),
                        SessionStatusType.valueOf(rs.getString(4))
                ),
                new CoverImage(rs.getString(5)),
                new SessionDate(
                        toLocalDateTime(rs.getTimestamp(6)),
                        toLocalDateTime(rs.getTimestamp(7))
                ),
                new Price(rs.getInt(8))
        ));
        return jdbcTemplate.query(sql, rowMapper, courseId);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
