package nextstep.courses.infrastructure;

import nextstep.courses.domain.Instructor;
import nextstep.courses.domain.InstructorRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import static nextstep.courses.infrastructure.util.LocalDateTimeFormatter.toLocalDateTime;

public class JdbcInstructorRepository implements InstructorRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcInstructorRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Instructor instructor) {
        String sql = "insert into instructor (" +
                "user_id, password, name, email, " +
                "created_at, updated_at " +
                ") values (" +
                "?, ?, ?, ?, ?, ? ) ";
        return jdbcTemplate.update(sql,
                instructor.getUserId(), instructor.getPassword(), instructor.getName(), instructor.getEmail(), instructor.getCreatedAt(), instructor.getUpdatedAt()
        );
    }

    @Override
    public Instructor findById(long instructorId) {
        String sql = "select id, user_id, password, name, email, created_at, updated_at " +
                "from instructor " +
                "where id = ? ";
        RowMapper<Instructor> rowMapper = (rs, rowNum) -> new Instructor(
                rs.getLong("id"),
                rs.getString("user_id"),
                rs.getString("password"),
                rs.getString("name"),
                rs.getString("email"),
                toLocalDateTime(rs.getTimestamp("created_at")),
                toLocalDateTime(rs.getTimestamp("updated_at"))
        );
        return jdbcTemplate.queryForObject(sql, rowMapper, instructorId);
    }
}
