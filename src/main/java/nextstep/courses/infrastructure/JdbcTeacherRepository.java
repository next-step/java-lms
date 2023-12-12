package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.Teacher;
import nextstep.courses.domain.session.repository.EnrolmentRepository;
import nextstep.courses.domain.session.repository.TeacherRepository;
import nextstep.users.domain.UserRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JdbcTeacherRepository implements TeacherRepository {

    private final JdbcOperations jdbcTemplate;
    private final UserRepository userRepository;
    private final EnrolmentRepository enrolmentRepository;

    public JdbcTeacherRepository(JdbcOperations jdbcTemplate, UserRepository userRepository, EnrolmentRepository enrolmentRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.userRepository = userRepository;
        this.enrolmentRepository = enrolmentRepository;
    }

    @Override
    public Optional<Teacher> findById(Long id) {
        String sql = "select * from teacher where id = ?";

        RowMapper<Teacher> rowMapper = (rs, rowNum) -> new Teacher(
            rs.getLong(1),
            userRepository.findByUserId(String.valueOf(rs.getLong(2))).orElseThrow(),
            enrolmentRepository.findById(rs.getLong(3)).orElseThrow()
        );

        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper, id));
    }
}
