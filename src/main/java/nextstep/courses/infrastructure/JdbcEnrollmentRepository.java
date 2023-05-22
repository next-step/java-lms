package nextstep.courses.infrastructure;

import nextstep.courses.domain.Enrollment;
import nextstep.courses.domain.repository.EnrollmentRepository;
import nextstep.users.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository("enrollmentRepository")
public class JdbcEnrollmentRepository implements EnrollmentRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcEnrollmentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long save(Enrollment enrollment) {
        String query = "INSERT INTO enrollment (maximum_enrollment) VALUES (?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, enrollment.getMaximumEnrollment());
            return ps;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public Enrollment findById(Long id) {
        String query = "SELECT * FROM enrollment WHERE id = ?";
        Enrollment enrollment = jdbcTemplate.queryForObject(query, (rs, rowNum) -> new Enrollment(
                rs.getLong("id"),
                rs.getInt("maximum_enrollment")
        ), id);

        String userQuery = "SELECT u.* FROM next_step_user u INNER JOIN enrollment_user eu ON u.id = eu.user_id WHERE eu.enrollment_id = ?";
        List<User> users = jdbcTemplate.query(userQuery, new UserRowMapper(), id);
        enrollment.getUsers().addAll(users);

        return enrollment;
    }

    @Override
    public int update(Enrollment enrollment) {
        String query = "UPDATE enrollment SET maximum_enrollment = ? WHERE id = ?";
        return jdbcTemplate.update(query, enrollment.getMaximumEnrollment(), enrollment.getId());
    }

    @Override
    public int delete(Long id) {
        String query = "DELETE FROM enrollment WHERE id = ?";
        return jdbcTemplate.update(query, id);
    }

    private static class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
            return new User(
                    rs.getLong("id"),
                    rs.getString("user_id"),
                    rs.getString("password"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getTimestamp("created_at").toLocalDateTime(),
                    rs.getTimestamp("updated_at").toLocalDateTime()
            );
        }
    }
}
