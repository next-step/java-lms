package nextstep.courses.infrastructure;

import nextstep.courses.domain.model.Session;
import nextstep.courses.domain.model.SessionImage;
import nextstep.courses.domain.repository.SessionRepository;
import nextstep.courses.infrastructure.entity.JdbcCourse;
import nextstep.courses.infrastructure.entity.JdbcSession;
import nextstep.courses.infrastructure.entity.JdbcSessionImage;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class JdbcSessionRepository implements SessionRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcSessionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long save(Session session) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("session")
                .usingGeneratedKeyColumns("id");

        Number sessionId = simpleJdbcInsert.executeAndReturnKey(session.getParameters());
        session.setId(sessionId.longValue());

        simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("session_image")
                .usingGeneratedKeyColumns("id");

        for (SessionImage image : session.getImages()) {
            simpleJdbcInsert.execute(image.getParameters(sessionId.longValue()));
        }

        return sessionId.intValue();
    }


    @Override
    public Session findById(Long id) {
        String sql = "select * from session where id = ?";
        JdbcSession entity = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(JdbcSession.class), id);

        sql = "select * from session_image where session_id = ?";
        List<JdbcSessionImage> images = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(JdbcSessionImage.class), id);
        List<SessionImage> sessionImages = images.stream()
                .map(JdbcSessionImage::toDomain)
                .collect(Collectors.toList());

        sql = "select * from course where id = ?";
        JdbcCourse course = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(JdbcCourse.class), entity.getCourseId());

        return entity.toDomain(course.toDomain(), sessionImages);
    }

}
