package nextstep.courses.infrastructure;

import nextstep.courses.domain.Students;
import nextstep.courses.repository.StudentsRepository;
import nextstep.users.domain.NsUser;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Set;

@Repository("studentsRepository")
public class JdbcStudentsRepository implements StudentsRepository {

    private final JdbcOperations jdbcTemplate;

    public JdbcStudentsRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long save(Long id, Long sessionId) {
        String sql = "insert into students (session_id, user_id, created_at) values (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setLong(1, sessionId);
            ps.setLong(2, id);
            ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    @Override
    public Students findBySessionId(Long id) {
        String sql = "select ns.id, ns.user_id, ns.password, ns.name, ns.email, ns.created_at, ns.updated_at from students st inner join ns_user ns where st.user_id = ns.id and st.session_id = ?";
        RowMapper<NsUser> rowMapper = (rs, rowNum) -> new NsUser(
                rs.getLong(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5),
                toLocalDateTime(rs.getTimestamp(6)),
                toLocalDateTime(rs.getTimestamp(7))
        );

        return new Students(Set.of(jdbcTemplate.queryForObject(sql, rowMapper, id)));
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
