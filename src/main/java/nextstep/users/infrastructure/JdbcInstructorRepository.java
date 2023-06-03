package nextstep.users.infrastructure;

import nextstep.users.domain.Instructor;
import nextstep.users.domain.InstructorRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcInstructorRepository implements InstructorRepository {

    private final JdbcOperations jdbcOperations;

    public JdbcInstructorRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public int save(Instructor instructor) {
        String sql = "insert into instructor(ns_user_id, session_id) values (?, ?);";
        return jdbcOperations.update(sql, instructor.getNsUserId(), instructor.getSessionId());
    }

    @Override
    public Instructor findById(long id) {
        String sql = "select id, ns_user_id, session_id from instructor where id = ?";
        RowMapper<Instructor> rowMapper = (rs, rowNum) -> new Instructor(
                rs.getLong(1),
                rs.getLong(2),
                rs.getLong(3));
        return jdbcOperations.queryForObject(sql, rowMapper, id);
    }
}
