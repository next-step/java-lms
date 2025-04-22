package nextstep.courses.infrastructure;

import nextstep.courses.domain.model.Session;
import nextstep.courses.domain.model.SessionImage;
import nextstep.courses.domain.model.SessionPeriod;
import nextstep.courses.domain.model.Student;
import nextstep.courses.domain.repository.StudentRepository;
import nextstep.users.domain.NsUser;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.io.IOException;
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
        String sql = "select s.id, s.created_at, s.updated_at," +
                "n.id as session_id ,n.course_id ,n.start_date ,n.end_date ,n.image_path ,n.image_file , n.status ,n.price,n.capacity ,n.creator_id ,n.created_at as session_created_at,n.updated_at as session_updated_at," +
                "u.id as ns_user_id, u.user_id, u.password, u.name, u.email, u.balance, u.created_at as user_created_at, u.updated_at as user_updated_at " +
                "from student s " +
                "join session n on s.session_id = n.id " +
                "join ns_user u on s.ns_user_id = u.id " +
                "where s.id = ?";

        RowMapper<Student> rowMapper = (rs, rowNum) -> {
            try {
                return new Student(
                        rs.getLong("id"),
                        new NsUser(
                                rs.getLong("ns_user_id"),
                                rs.getString("user_id"),
                                rs.getString("password"),
                                rs.getString("name"),
                                rs.getString("email"),
                                rs.getBigDecimal("balance"),
                                rs.getTimestamp("user_created_at"),
                                rs.getTimestamp("user_updated_at")),
                        new Session(
                                rs.getLong("session_id"),
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
                                rs.getTimestamp("session_created_at"),
                                rs.getTimestamp("session_updated_at")),
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("updated_at"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

}
