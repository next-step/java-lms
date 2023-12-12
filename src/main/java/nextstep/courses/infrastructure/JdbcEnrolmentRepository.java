package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.enroll.EnrollStatus;
import nextstep.courses.domain.session.enroll.Enrolment;
import nextstep.courses.domain.session.repository.EnrolmentRepository;
import nextstep.courses.domain.session.repository.StudentRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JdbcEnrolmentRepository implements EnrolmentRepository {

    private final JdbcOperations jdbcTemplate;
    private final StudentRepository studentRepository;

    public JdbcEnrolmentRepository(JdbcOperations jdbcTemplate, StudentRepository studentRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.studentRepository = studentRepository;
    }

    @Override
    public Optional<Enrolment> findBySession(Long sessionId) {
        String sql = "select * from enrolment where session_id = ?";

        RowMapper<Enrolment> rowMapper = (rs, rowNum) -> new Enrolment(
            rs.getLong(1),
            studentRepository.findAllByEnrolment(sessionId),
            EnrollStatus.valueOf(rs.getString(3))
        );

        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper, sessionId));
    }
}
