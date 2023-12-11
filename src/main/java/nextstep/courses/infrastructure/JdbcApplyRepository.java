package nextstep.courses.infrastructure;

import nextstep.courses.domain.participant.Participant;
import nextstep.courses.domain.participant.ParticipantState;
import nextstep.courses.repository.ApplyRepository;
import nextstep.users.domain.NsUser;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository("sessionRepository")
public class JdbcApplyRepository implements ApplyRepository {

    private final JdbcOperations jdbcTemplate;

    public JdbcApplyRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Long sessionId, NsUser nsUser, ParticipantState state) {
        String sql = "insert into apply (session_id, user_id, state, created_at) values(?, ?, ?, ?)";
        return jdbcTemplate.update(sql, sessionId, nsUser.getId(), state.toString(), LocalDateTime.now());
    }


    @Override
    public List<Participant> findBySessionId(Long sessionId) {
        String sql = "select ns.id, ns.user_id, ns.password, ns.name, ns.email, ns.created_at, ns.updated_at, apply.state from apply inner join ns_user ns where apply.user_id = ns.id and apply.session_id = ?";
        RowMapper<Participant> rowMapper = (rs, rowNum) -> new Participant(
                new NsUser(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        toLocalDateTime(rs.getTimestamp(6)),
                        toLocalDateTime(rs.getTimestamp(7))),
                ParticipantState.valueOf(rs.getString(8))
        );
        return jdbcTemplate.query(sql, rowMapper, sessionId);
    }

    @Override
    public int updateState(Long id, ParticipantState participantState) {
        String sql = "update apply set state = ? where id = ?";
        return jdbcTemplate.update(sql, participantState.toString(), id);
    }


    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
