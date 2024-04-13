package nextstep.session.infrastructure;

import nextstep.session.domain.Student;
import nextstep.session.domain.StudentRepository;
import nextstep.session.dto.StudentVO;
import nextstep.utils.DbTimestampUtils;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository("studentRepository")
public class JdbcStudentRepository implements StudentRepository {

    public static final String STUDENTS_INSERT_QUERY = "insert into student (session_id, ns_user_id, deleted, created_at, last_modified_at) values(?, ?, ?, ?, ?)";
    public static final String STUDENTS_FIND_QUERY =
            "select id as id, " +
                    "session_id as sessionId, " +
                    "ns_user_id as userId, " +
                    "deleted as deleted, " +
                    "created_at as createdAt, " +
                    "last_modified_at as lastModifiedAt " +
            "from student " +
            "where session_id = ? and deleted = false";

    private JdbcOperations jdbcTemplate;

    public JdbcStudentRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Student> findBySessionId(long sessionId) {
        RowMapper<Student> rowMapper = (rs, rowNum) -> new Student(
                rs.getLong("id"),
                rs.getLong("sessionId"),
                rs.getString("userId"),
                rs.getBoolean("deleted"),
                DbTimestampUtils.toLocalDateTime(rs.getTimestamp("createdAt")),
                DbTimestampUtils.toLocalDateTime(rs.getTimestamp("lastModifiedAt")));
        return jdbcTemplate.query(STUDENTS_FIND_QUERY, new Object[]{sessionId}, rowMapper);
    }

    @Override
    public long save(Student student) {
        StudentVO studentVO = student.toVO();
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(STUDENTS_INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, studentVO.getSessionId());
            ps.setString(2, studentVO.getUserId());
            ps.setBoolean(3, studentVO.isDeleted());
            ps.setTimestamp(4, DbTimestampUtils.toTimestamp(studentVO.getCreatedAt()));
            ps.setTimestamp(5, DbTimestampUtils.toTimestamp(studentVO.getLastModifiedAt()));
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    @Override
    public int updateDeleteStatus(long sessionId, String studentId, boolean deleteStatus) {
        String sql = "UPDATE student SET deleted = ? WHERE session_id = ? AND ns_user_id = ?";
        return jdbcTemplate.update(sql, deleteStatus, sessionId, studentId);
    }
}
