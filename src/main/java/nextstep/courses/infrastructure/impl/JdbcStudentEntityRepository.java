package nextstep.courses.infrastructure.impl;

import nextstep.courses.entity.StudentEntity;
import nextstep.courses.infrastructure.StudentRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

@Repository("studentRepository")
public class JdbcStudentEntityRepository implements StudentRepository {

    private JdbcOperations jdbcTemplate;

    public JdbcStudentEntityRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(StudentEntity studentEntity) {
        String sql = "insert into student (name, email, payment_amount, approval_state, student_type, created_at) values (?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
            studentEntity.getStudentName(),
            studentEntity.getEmail(),
            studentEntity.getPaymentAmount(),
            studentEntity.getApprovalState(),
            studentEntity.getStudentType(),
            studentEntity.getCreatedAt());
    }
}
