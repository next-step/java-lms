package nextstep.session.infrastructure;

import nextstep.session.domain.Session;
import nextstep.session.domain.SessionRepository;
import nextstep.users.domain.NsUser;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {

    private JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public int save(Session session) {
        String sql = "insert into session (amount, session_type, session_status, image_id, start_at, end_at) values (?, ?, ?, ?, ?, ?)";
        Long imageId = null;
        if (session.getCoverImage() != null) {
            imageId = session.getCoverImage().getId();
        }
        int sessionId = jdbcTemplate.update(sql, session.getAmount(), session.getSessionType().name(), session.getStatus().name(), imageId, session.getBaseTime().getStartAt(), session.getBaseTime().getEndAt());
        refreshAttendees(session, sessionId);

        return sessionId;
    }

    private void refreshAttendees(Session session, int sessionId) {
        String deleteAttendeesSql = "delete from session_attendee where session_id = ?";
        jdbcTemplate.update(deleteAttendeesSql, sessionId);

        String memberSql = "insert into session_attendee (ns_user_id, session_id) values (?, ?)";
        List<NsUser> nsUsers = new ArrayList<>(session.getMembers().values());
        jdbcTemplate.batchUpdate(memberSql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setLong(1, nsUsers.get(i).getId());
                ps.setString(2, String.valueOf(sessionId));
            }

            @Override
            public int getBatchSize() {
                return nsUsers.size();
            }
        });
    }
}
