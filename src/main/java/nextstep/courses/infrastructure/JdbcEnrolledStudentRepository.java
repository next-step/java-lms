package nextstep.courses.infrastructure;

import nextstep.courses.domain.EnrolledStudent;
import nextstep.courses.domain.EnrolledStudentRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcEnrolledStudentRepository implements EnrolledStudentRepository {

    private final JdbcOperations jdbcTemplate;

    public JdbcEnrolledStudentRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(EnrolledStudent enrolledStudent) {
        String sql = "INSERT INTO enrolled_student (session_id, user_id, enrolled_at) VALUES (?, ?, ?)";
        jdbcTemplate.update(
                sql,
                enrolledStudent.getSession().getId(),   // session 객체에서 id 꺼냄
                enrolledStudent.getId(),
                enrolledStudent.getEnrolledAt()
        );
    }
}