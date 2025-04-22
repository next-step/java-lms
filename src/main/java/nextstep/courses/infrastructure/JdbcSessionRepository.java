package nextstep.courses.infrastructure;

import nextstep.courses.domain.model.Session;
import nextstep.courses.domain.model.SessionImage;
import nextstep.courses.domain.model.SessionPeriod;
import nextstep.courses.domain.repository.SessionRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Repository
public class JdbcSessionRepository implements SessionRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcSessionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Session session) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("session")
                .usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("course_id", session.getCourseId());
        parameters.put("capacity", session.getStudents().getCapacity());
        parameters.put("status", session.getStatus().name());
        parameters.put("price", new BigDecimal(session.getPrice()));
        parameters.put("start_date", session.getPeriod().getStartDate().toLocalDate());
        parameters.put("end_date", session.getPeriod().getEndDate().toLocalDate());
        parameters.put("image_path", session.getImage().getPath());
        parameters.put("image_file", session.getImage().getFile());
        parameters.put("creator_id", session.getCreatorId());
        parameters.put("created_at", session.getCreatedAt());
        parameters.put("updated_at", session.getUpdatedAt());

        return simpleJdbcInsert.execute(parameters);
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
