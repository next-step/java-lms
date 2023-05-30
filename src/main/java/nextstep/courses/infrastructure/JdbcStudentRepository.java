package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository("studentRepository")
public class JdbcStudentRepository implements StudentRepository {

    private JdbcOperations jdbcTemplate;

    public JdbcStudentRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Student student) {
        String sql = "insert into student " +
                "(session_id, user_id, creator_id, created_at) " +
                "values(?, ?, ?, ?)";
        return jdbcTemplate.update(
                sql
                , student.getSessionId()
                , student.getUserId()
                , student.getCreatorId()
                , student.getCreatedAt()
        );
    }

    @Override
    public Student findById(int sessionId, Long userId) {
        String sql = "select session_id, user_id, creator_id, created_at, updated_at" +
                " from student" +
                " where session_id = ? and user_id = ?";

        RowMapper<Student> rowMapper = (rs, rowNum) -> new Student(
                rs.getInt(1),
                rs.getLong(2),
                rs.getLong(3),
                toLocalDateTime(rs.getTimestamp(4)),
                toLocalDateTime(rs.getTimestamp(5))
        );

        return jdbcTemplate.queryForObject(sql, rowMapper, sessionId, userId);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
