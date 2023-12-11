package nextstep.courses.infrastructure;

import nextstep.courses.domain.Participants;
import nextstep.courses.repository.ApplyRepository;
import nextstep.users.domain.NsUser;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository("sessionRepository")
public class JdbcApplyRepository implements ApplyRepository {

    private final JdbcOperations jdbcTemplate;

    public JdbcApplyRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(long sessionId, NsUser nsUser) {
        String sql = "insert into apply (session_id, user_id, created_at) values(?, ?, ?)";
        return jdbcTemplate.update(sql, sessionId, nsUser.getId(), LocalDateTime.now());
    }


    @Override
    public Participants findBySessionId(Long sessionId) {
        String sql = "select ns.id, ns.user_id, ns.password, ns.name, ns.email, ns.created_at, ns.updated_at from apply inner join ns_user ns where apply.user_id = ns.id and apply.session_id = ?";
        RowMapper<NsUser> rowMapper = (rs, rowNum) -> new NsUser(
                rs.getLong(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5),
                toLocalDateTime(rs.getTimestamp(6)),
                toLocalDateTime(rs.getTimestamp(7)));
        return new Participants(jdbcTemplate.query(sql, rowMapper, sessionId));
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
