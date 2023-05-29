package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JdbcSessionRepository implements SessionRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Session session) {
        String sql = "insert into session (course_id, title, cover, cardinal_number, cost, state, max_user) values(?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, session.getCourseId(), session.getTitle(), session.getCover(), session.getCardinalNumber()
                , session.getCost().getCode(), session.getSessionRegistration().getState().getCode()
                , session.getSessionRegistration().getMaxUser());
    }

    @Override
    public Optional<Session> findById(long id) {
        String sql = "select id, course_id, title, cover, cardinal_number, cost, state, max_user from session where id = ?";
        RowMapper<Session> rowMapper = (rs, rowNum) -> Session.of(
                rs.getLong(1),
                rs.getLong(2),
                rs.getString(3),
                rs.getString(4),
                rs.getInt(5),
                Cost.valueOf(rs.getString(6)),
                State.valueOf(rs.getString(7)),
                rs.getInt(8));
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper, id));
    }
}
