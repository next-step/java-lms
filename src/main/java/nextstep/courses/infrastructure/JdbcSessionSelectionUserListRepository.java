package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.SelectStatus;
import nextstep.courses.domain.session.SelectionUser;
import nextstep.courses.domain.session.SessionSelectionUserListRepository;
import nextstep.users.domain.NsUser;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository("sessionSelectionUserListRepository")
public class JdbcSessionSelectionUserListRepository implements SessionSelectionUserListRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcSessionSelectionUserListRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(final SelectionUser user, final Long sessionId) {
        Long userId = user.getUserId();
        String selectStatus = user.getSelectStatusString();

        String sql = "insert into session_selection_user_list (user_id, session_id, select_status) values (?, ?, ?)";
        return jdbcTemplate.update(sql, userId, sessionId, selectStatus);
    }

    @Override
    public List<SelectionUser> findAllBySessionId(final Long sessionId) {
        String sql = "select u.id, u.user_id, u.password, u.name, u.email, u.created_at, u.updated_at, ssu.select_status " +
                "from session_selection_user_list ssu " +
                "inner join ns_user u " +
                "on ssu.user_id = u.id " +
                "where ssu.session_id = ?";

        RowMapper<SelectionUser> rowMapper = (rs, rowNum) -> {
            final NsUser nsUser = new NsUser(
                    rs.getLong(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    toLocalDateTime(rs.getTimestamp(6)),
                    toLocalDateTime(rs.getTimestamp(7))
            );

            return new SelectionUser(SelectStatus.valueOf(rs.getString(8)), nsUser);
        };

        return jdbcTemplate.query(sql, rowMapper, sessionId);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
