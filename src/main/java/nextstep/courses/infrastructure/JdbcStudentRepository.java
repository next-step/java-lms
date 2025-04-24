package nextstep.courses.infrastructure;

import nextstep.courses.domain.model.Session;
import nextstep.courses.domain.model.SessionImage;
import nextstep.courses.domain.model.Student;
import nextstep.courses.domain.repository.StudentRepository;
import nextstep.courses.infrastructure.entity.JdbcSession;
import nextstep.courses.infrastructure.entity.JdbcSessionImage;
import nextstep.courses.infrastructure.entity.JdbcStudent;
import nextstep.users.domain.NsUser;
import nextstep.users.infrastructure.entity.NsUserEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class JdbcStudentRepository implements StudentRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcStudentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Student student) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("student")
                .usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("session_id", student.getSession().getId());
        parameters.put("ns_user_id", student.getNsUser().getId());
        parameters.put("created_at", student.getCreatedAt());

        return simpleJdbcInsert.execute(parameters);
    }

    @Override
    public Student findById(Long id) {
        String studentSql = "SELECT * FROM student WHERE id = ?";
        JdbcStudent entity = jdbcTemplate.queryForObject(studentSql, new BeanPropertyRowMapper<>(JdbcStudent.class), id);

        NsUser nsUser = findNsUserById(entity.getNsUserId());
        Session session = findSessionById(entity.getSessionId());

        return entity.toDomain(nsUser, session);
    }

    private NsUser findNsUserById(Long nsUserId) {
        String userSql = "SELECT * FROM ns_user WHERE id = ?";
        NsUserEntity nsUser = jdbcTemplate.queryForObject(userSql, new BeanPropertyRowMapper<>(NsUserEntity.class), nsUserId);
        return nsUser.toDomain();
    }

    private Session findSessionById(Long sessionId) {
        String sql = "SELECT * FROM session WHERE id = ?";
        JdbcSession entity = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(JdbcSession.class), sessionId);


        sql = "select * from session_image where session_id = ?";
        List<JdbcSessionImage> images = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(JdbcSessionImage.class), sessionId);
        List<SessionImage> sessionImages = images.stream()
                .map(JdbcSessionImage::toDomain)
                .collect(Collectors.toList());
        return entity.toDomain(sessionImages);
    }

}
