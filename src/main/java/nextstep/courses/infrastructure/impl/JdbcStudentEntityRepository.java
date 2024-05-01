package nextstep.courses.infrastructure.impl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
import nextstep.courses.entity.SessionEntity;
import nextstep.courses.entity.StudentEntity;
import nextstep.courses.infrastructure.StudentRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("studentRepository")
public class JdbcStudentEntityRepository implements StudentRepository {

    private JdbcOperations jdbcTemplate;

    public JdbcStudentEntityRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(StudentEntity studentEntity) {
        String sql = "insert into student (name, email, approval_state, student_type, created_at) values (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
            studentEntity.getStudentName(),
            studentEntity.getEmail(),
            studentEntity.getApprovalState(),
            studentEntity.getStudentType(),
            studentEntity.getCreatedAt());
    }

    @Override
    public Optional<StudentEntity> findById(Long id) {
        String sql = "select id, name, email, approval_state, student_type, created_at, updated_at from student where id = ?";
        RowMapper<StudentEntity> rowMapper = (rs, rowNum) -> new StudentEntity (
            rs.getLong(1),
            rs.getString(2),
            rs.getString(3),
            rs.getString(4),
            rs.getString(5),
            toLocalDateTime(rs.getTimestamp(6)),
            toLocalDateTime(rs.getTimestamp(7)));

        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper, id));
    }

    @Override
    public int updateApprovalState(StudentEntity studentEntity) {
        String sql = "UPDATE student SET approval_state = ? WHERE id = ?";
        return jdbcTemplate.update(sql, studentEntity.getApprovalState(),
            studentEntity.getId());
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
