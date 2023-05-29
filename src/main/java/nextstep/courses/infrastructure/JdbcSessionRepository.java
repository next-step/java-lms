package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;

public class JdbcSessionRepository implements SessionRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Session session) {
        String sql = "insert into session (title, cover, cardinal_number, cost, state, max_user) values(?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, session.getTitle(), session.getCover(), session.getCardinalNumber()
                , session.getCost().getCode(), session.getSessionRegistration().getState().getCode()
                , session.getSessionRegistration().getMaxUser());
    }

    @Override
    public Session findById(long id) {
        String sql = "select id, title, cover, cardinal_number, cost, state, max_user from session where id = ?";
        RowMapper<Session> rowMapper = (rs, rowNum) -> Session.of(
                rs.getLong(1),
                rs.getString(2),
                rs.getString(3),
                rs.getInt(4),
                Cost.valueOf(rs.getString(5)),
                State.valueOf(rs.getString(6)),
                rs.getInt(7));
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }
}
