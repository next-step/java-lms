package nextstep.courses.infrastructure;

import nextstep.courses.domain.model.Session;
import nextstep.courses.domain.model.SessionImage;
import nextstep.courses.domain.model.SessionPeriod;
import nextstep.courses.domain.repository.SessionRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.math.BigDecimal;

@Repository
public class JdbcSessionRepository implements SessionRepository {
    private final JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Session session) {
        String sql = "insert into session (course_id, capacity, status, price, start_date, end_date, image_path, image_file, creator_id, created_at, updated_at) "
                + "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        return jdbcTemplate.update(sql,
                session.getCourseId(),
                session.getStudents().getCapacity(),
                session.getStatus().name(),
                new BigDecimal(session.getPrice()),
                session.getPeriod().getStartDate().toLocalDate(),
                session.getPeriod().getEndDate().toLocalDate(),
                session.getImage().getPath(),
                session.getImage().getFile(),
                session.getCreatorId(),
                session.getCreatedAt(),
                session.getUpdatedAt());
    }

    @Override
    public Session findById(Long id) {
        String sql = "select id, course_id, capacity, status, price, start_date, end_date, image_path, image_file, creator_id, created_at, updated_at from session where id = ?";
        RowMapper<Session> rowMapper = (rs, rowNum) -> {
            try {
                return new Session(
                        rs.getLong("id"),
                        rs.getLong("course_id"),
                        new SessionPeriod(
                                rs.getDate("start_date"),
                                rs.getDate("end_date")
                        ),
                        new SessionImage(
                                rs.getString("image_path"),
                                rs.getBlob("image_file")
                        ),
                        rs.getString("status"),
                        rs.getLong("price"),
                        rs.getInt("capacity"),
                        rs.getLong("creator_id"),
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("updated_at"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

}
