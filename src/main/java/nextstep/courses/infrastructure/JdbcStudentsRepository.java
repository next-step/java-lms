package nextstep.courses.infrastructure;

import nextstep.courses.domain.Students;
import nextstep.courses.repository.StudentsRepository;
import nextstep.users.domain.NsUser;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Set;

@Repository("studentsRepository")
public class JdbcStudentsRepository implements StudentsRepository {

    private final JdbcOperations jdbcTemplate;

    public JdbcStudentsRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Long id, Long sessionId) {
        String sql = "insert into students (session_id, user_id, created_at) values (?, ?, ?)";
        return jdbcTemplate.update(sql, sessionId, id, LocalDateTime.now());
    }

    @Override
    public Students findBySessionId(Long id) {
        String sql = "select ns.id, ns.user_id, ns.password, ns.name, ns.email from students st inner join ns_user ns where st.user_id = ns.id and st.session_id = ?";
        RowMapper<NsUser> rowMapper = (rs, rowNum) -> new NsUser(
                rs.getLong(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5)
        );

        return new Students(Set.of(jdbcTemplate.queryForObject(sql, rowMapper, id)));
    }
}
