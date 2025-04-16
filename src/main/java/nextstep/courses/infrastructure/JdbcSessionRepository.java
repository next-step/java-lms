package nextstep.courses.infrastructure;

import nextstep.courses.domain.Image;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import nextstep.courses.domain.SessionStatus;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcSessionRepository implements SessionRepository {

    private final JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Session session, Long courseId) {
        String sql = "insert into session " +
                "(title, start_date, end_date, tuition, current_count, capacity, " +
                "image_file_size, image_file_type, image_url, image_width, image_height, " +
                "status, course_id) " +
                "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        return jdbcTemplate.update(sql,
                session.getTitle(),
                session.getStartDate(),
                session.getEndDate(),
                session.getTuition(),
                session.getCurrentCount(),
                session.getCapacity(),
                session.getCoverImage().getSize(),
                session.getCoverImage().getType(),
                session.getCoverImage().getImageUrl(),
                session.getCoverImage().getWidth(),
                session.getCoverImage().getHeight(),
                session.getStatus().name(),
                courseId
        );
    }

    @Override
    public Session findById(Long id) {
        String sql = "select * from session where id = ?";

        return jdbcTemplate.queryForObject(sql, sessionRowMapper(), id);
    }

    private RowMapper<Session> sessionRowMapper() {
        return (rs, rowNum) -> new Session(
                rs.getString("title"),
                rs.getInt("id"),
                rs.getTimestamp("start_date").toLocalDateTime(),
                rs.getTimestamp("end_date").toLocalDateTime(),
                rs.getLong("tuition"),
                rs.getInt("current_count"),
                rs.getInt("capacity"),
                new Image(
                        rs.getFloat("image_file_size"),
                        rs.getString("image_file_type"),
                        rs.getString("image_url"),
                        rs.getInt("image_width"),
                        rs.getInt("image_height")
                ),
                SessionStatus.valueOf(rs.getString("status"))
        );
    }
}
