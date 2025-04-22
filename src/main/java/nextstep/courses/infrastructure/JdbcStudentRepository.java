package nextstep.courses.infrastructure;

import nextstep.courses.domain.model.Session;
import nextstep.courses.domain.model.Student;
import nextstep.courses.domain.repository.StudentRepository;
import nextstep.users.domain.NsUser;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

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
        String studentSql = "SELECT id, session_id, ns_user_id, created_at, updated_at FROM student WHERE id = ?";
        Map<String, Object> studentMap = jdbcTemplate.queryForMap(studentSql, id);
        Long sessionId = ((Number) studentMap.get("session_id")).longValue();
        Long nsUserId = ((Number) studentMap.get("ns_user_id")).longValue();
        Timestamp stCreated = (Timestamp) studentMap.get("created_at");
        Timestamp stUpdated = (Timestamp) studentMap.get("updated_at");

        NsUser nsUser = findNsUserById(nsUserId);
        Session session = findSessionById(sessionId);

        return new Student(id, nsUser, session, stCreated, stUpdated);
    }

    private NsUser findNsUserById(Long nsUserId) {
        String userSql = "SELECT id, user_id, password, name, email, balance, created_at, updated_at FROM ns_user WHERE id = ?";
        NsUser nsUser = jdbcTemplate.queryForObject(userSql,
                (rs, rowNum) -> new NsUser(
                        rs.getLong("id"),
                        rs.getString("user_id"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getBigDecimal("balance"),
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("updated_at")
                ),
                nsUserId
        );
        return nsUser;
    }

    private Session findSessionById(Long sessionId) {
        String sessionSql = "SELECT id, course_id, start_date, end_date, image_path, image_file, status, price, capacity, creator_id, created_at, updated_at FROM session WHERE id = ?";
        Session session = jdbcTemplate.queryForObject(sessionSql,
                (rs, rowNum) -> {
                    try {
                        return new Session(
                                rs.getLong("id"),
                                rs.getLong("course_id"),
                                rs.getDate("start_date"),
                                rs.getDate("end_date"),
                                rs.getString("image_path"),
                                rs.getBlob("image_file"),
                                rs.getString("status"),
                                rs.getLong("price"),
                                rs.getInt("capacity"),
                                rs.getLong("creator_id"),
                                rs.getTimestamp("created_at"),
                                rs.getTimestamp("updated_at")
                        );
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                },
                sessionId
        );
        return session;
    }

}
