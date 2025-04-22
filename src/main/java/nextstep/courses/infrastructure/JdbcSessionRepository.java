package nextstep.courses.infrastructure;

import nextstep.courses.domain.model.Session;
import nextstep.courses.domain.repository.SessionRepository;
import nextstep.courses.infrastructure.entity.JdbcSession;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

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
        String sql = "select * from session where id = ?";
        JdbcSession entity = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(JdbcSession.class), id);
        return entity == null? null : entity.toDomain();
    }

}
