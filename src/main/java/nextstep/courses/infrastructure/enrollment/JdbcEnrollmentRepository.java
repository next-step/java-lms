package nextstep.courses.infrastructure.enrollment;

import java.time.LocalDateTime;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import nextstep.courses.domain.entity.Enrollment;
import nextstep.courses.domain.entity.NsCourse;
import nextstep.courses.domain.entity.NsSession;
import nextstep.courses.domain.repository.EnrollmentRepository;
import nextstep.courses.infrastructure.enrollment.mapper.EnrollmentRowMapper;
import nextstep.users.domain.NsUser;

@Repository("enrollmentRepository")
public class JdbcEnrollmentRepository implements EnrollmentRepository {

    private JdbcOperations jdbcTemplate;

    public JdbcEnrollmentRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(NsCourse nsCourse,
                    NsUser nsUser,
                    NsSession nsSession) {
        String sql = "insert into enrollment (course_id, user_id, session_id, created_at, updated_at) values(?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, nsCourse.getId(), nsUser.getId(), nsSession.getId(), LocalDateTime.now(), null);
    }

    @Override
    public Enrollment findById(Long id) {
        String sql = "select course_id, user_id, session_id, created_at, updated_at from enrollment where id = ?";
        return jdbcTemplate.queryForObject(sql, new EnrollmentRowMapper(), id);
    }
}
